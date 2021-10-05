import exchange.CommandStatus;
import exchange.User;
import org.w3c.dom.ls.LSOutput;
import stdgroup.GroupEnter;
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
    private User user;
    private SocketChannel socketChannel;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    private int attempts=10;

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
                    logging();
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
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            System.out.println( "Работа клиента завершена." );
        }  catch (IOException e) {
        }
    }

    private void logging() throws IOException, ClassNotFoundException {
        Response response;
        do {
            System.out.println( "Войти или создать новый аккаунт? (log или reg)" );
            String login, pass;
            boolean newUser;
            loop:
            do {
                switch (userHandler.getGroupEnter().read()) {
                    case "log":
                        newUser = false;
                        break loop;
                    case "reg":
                        newUser = true;
                        break loop;
                    default:
                        System.out.println( "Неверная команда" );
                }
            } while (true);
            System.out.println( "Введите логин" );
            login = userHandler.getGroupEnter().read();
            System.out.println( "Введите пароль" );
            pass = userHandler.getGroupEnter().read();
            user = new User( login , pass , newUser );
            serverWriter.writeObject( new Request( newUser , user ) );
            response = (Response) serverReader.readObject();
            System.out.println(response.getResponse());
        } while (response.getCommandStatus() != CommandStatus.LOGGED);
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
                request.setUser( user );
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

    private void sendRequest (Request request) throws IOException{
        serverWriter.reset();
        serverWriter.writeObject(request);
    }

    private void responseHandle(Response response, Request request) throws IOException{
        switch (response.getCommandStatus()) {
            case OK:
                System.out.println( response.getResponse() );
                break;
            case NEED_GROUP:
                request.setRawGroup( userHandler.getGroupEnter().getStudyGroup() );
                sendRequest( request);
                break;
            case NEED_PERSON:
                request.setPerson( userHandler.getGroupEnter().getGroupAdmin() );
                sendRequest( request );
                break;
            case UPDATE:
                request.setUpdedGroup( userHandler.getGroupEnter().change(response.getUpdGroup()) );
                sendRequest( request );
                break;
            case ERROR:
                System.err.println( response.getResponse() );
                break;
        }
    }
}
