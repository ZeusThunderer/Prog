package client;

import client.utils.UserHandler;
import common.exchange.Request;
import common.exchange.Response;

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
                responseHandle( (Response) serverReader.readObject(), request );
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
        serverWriter.flush();
        serverWriter.writeObject( request);
    }

    private void responseHandle(Response response, Request request) throws IOException{
        switch (response.getCommandStatus()) {
            case OK:
                System.out.println( response.getResponse() );
                break;
            case NEED_GROUP:
                request.setObject( userHandler.getGroupEnter().getStudyGroup() );
                sendRequest( request );
                break;
            case NEED_PERSON:
                request.setObject( userHandler.getGroupEnter().getGroupAdmin() );
                sendRequest( request );
                break;
            case ERROR:
                System.err.println( response.getResponse() );
                break;
        }
    }
}
