import exchange.CommandStatus;
import utils.UserHandler;
import exchange.Request;
import exchange.Response;


import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;


public class Client {
    private String host;
    private int port;
    private UserHandler userHandler;
    private SocketChannel socketChannel;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    private int attempts=5;

    public Client(String host , int port , UserHandler userHandler) {
        this.host = host;
        this.port = port;
        this.userHandler = userHandler;
    }

    public void run() {
        try {
            while (true) {
                try {
                    connectToServer();
                    if (!execution())
                        break;
                    if (socketChannel != null) socketChannel.close();
                } catch (ConnectException e) {
                    if (attempts >0) {
                        System.err.println( "Будет произведена повторная попытка подключения" );
                        attempts--;
                    }
                    else
                        break;
                    try {
                        Thread.sleep( 1000 );
                    } catch (InterruptedException interruptedException) {
                    }
                }
            }
            System.out.println( "Работа клиента завершена." );
        } catch (IOException e) {
        }
    }

    private void connectToServer() throws ConnectException {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect( new InetSocketAddress( host , port ) );
            System.out.println( "Соединение с сервером успешно установлено." );
            System.out.println( "Ожидание разрешения на обмен данными..." );
            serverWriter = new ObjectOutputStream( socketChannel.socket().getOutputStream() );
            serverReader = new ObjectInputStream( socketChannel.socket().getInputStream() );
            System.out.println( "Разрешение на обмен данными получено." );
        } catch (IOException e) {
            System.err.println( "Произошла ошибка при подключении" );
            throw new ConnectException();
        }
    }
    private boolean execution() throws ConnectException {
        Request request;
        Response response;
        while (true){
            try {
                request = userHandler.handle();
                if (request.getCommandType().equals( "exit" ))
                    return false;
                sendRequest( request );
                response = (Response) serverReader.readObject();
                responseHandle( response, request );
                if (response.getCommandStatus() == CommandStatus.NEED_GROUP || response.getCommandStatus() == CommandStatus.NEED_PERSON) {
                    response = (Response) serverReader.readObject();
                    responseHandle(response, request);
                }
            } catch (InvalidClassException | NotSerializableException exception) {
                System.err.println("Произошла ошибка при отправке данных на сервер!");
            } catch (ClassNotFoundException e) {
                System.err.println("Произошла ошибка при чтении полученных данных!");
            } catch (IOException e) {
                System.err.println("Соединение с сервером разорвано!");
                connectToServer();
            }

        }
    }

    private void sendRequest (Serializable request) throws IOException{
        serverWriter.flush();
        serverWriter.writeObject(request);
        serverWriter.flush();
    }

    private void responseHandle(Response response, Request request) throws IOException{
        switch (response.getCommandStatus()) {
            case OK:
                System.out.println( response.getResponse() );
                break;
            case NEED_GROUP:
                request.setObject( userHandler.getGroupEnter().getStudyGroup() );
                sendRequest( request.getObject() );
                break;
            case NEED_PERSON:
                request.setObject( userHandler.getGroupEnter().getGroupAdmin() );
                sendRequest( request.getObject() );
                break;
            case ERROR:
                System.err.println( response.getResponse() );
                break;
        }
    }
}
