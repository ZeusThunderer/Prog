import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;

import stdgroup.Person;
import stdgroup.RawGroup;
import utils.RequestHandler;

import java.io.*;
import java.net.*;


public class Server {
    private int port;
    private int soTimeout;
    private ServerSocket serverSocket;
    private RequestHandler requestHandler;

    public Server(int port, int soTimeout, RequestHandler requestHandler) {
        this.port = port;
        this.soTimeout = soTimeout;
        this.requestHandler = requestHandler;
    }

    /**
     * Begins server operation.
     */
    public void run() {
            openServerSocket();
            boolean processingStatus = true;
            while (processingStatus) {
                try (Socket clientSocket = connectToClient()) {
                    processingStatus = processClientRequest(clientSocket);
                } catch (SocketTimeoutException exception) {
                    break;
                } catch (IOException exception) {
                    System.err.println("Произошла ошибка при попытке завершить соединение с клиентом!");
                }
            }
            stop();
    }

    /**
     * Finishes server operation.
     */
    private void stop() {
        try {
            serverSocket.close();
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при завершении работы сервера!");
        }
    }

    /**
     * Open server socket.
     */
    private void openServerSocket(){
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(soTimeout);
            System.out.println("сервер поднят");
        } catch (IllegalArgumentException exception) {
            System.err.println("Порт '" + port + "' находится за пределами возможных значений!");
        } catch (IOException exception) {
            System.err.println("Произошла ошибка при попытке использовать порт '" + port + "'!");
        }
    }

    /**
     * Connecting to client.
     */
    private Socket connectToClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }

    /**
     * The process of receiving a request from a client.
     */
    private boolean processClientRequest(Socket clientSocket) {
        Request userRequest = null;
        Response responseToUser = null;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream())) {
            do {
                userRequest = (Request) clientReader.readObject();
                System.out.println(userRequest.getCommandType());
                responseToUser = new Response(requestHandler.check( userRequest ));
                if (responseToUser.getCommandStatus()==CommandStatus.ERROR)
                    responseToUser = new Response(CommandStatus.ERROR, "Несущетвующая команда, для списка команд введите help");
                else if (responseToUser.getCommandStatus()!=null) {
                    clientWriter.writeObject( responseToUser );
                    clientWriter.flush();
                    /*userRequest = (Request) clientReader.readObject();*/
                    if (responseToUser.getCommandStatus() == CommandStatus.NEED_GROUP) {
                        RawGroup rawGroup = (RawGroup) clientReader.readObject();
                        userRequest.setObject( rawGroup );
                    }
                    else if (responseToUser.getCommandStatus() == CommandStatus.NEED_PERSON) {
                        Person person = (Person) clientReader.readObject();
                        userRequest.setObject( person );
                    }
                    responseToUser = requestHandler.handle( userRequest );
                }
                else {
                    responseToUser = requestHandler.handle( userRequest );
                    System.out.println( "Запрос '" + userRequest.getCommandType() + "' успешно обработан." );
                }
               clientWriter.writeObject(responseToUser);
                clientWriter.flush();

            } while (responseToUser.getCommandStatus() != CommandStatus.EXIT);
            return false;
        } catch (ClassNotFoundException exception) {
            System.err.println("Произошла ошибка при чтении полученных данных!");
        } catch (InvalidClassException | NotSerializableException exception) {
            System.err.println("Произошла ошибка при отправке данных на клиент!");
        } catch (IOException exception) {
            if (userRequest == null) {
                System.err.println("Непредвиденный разрыв соединения с клиентом!");
            } else {
                System.out.println("Клиент успешно отключен от сервера!");
                requestHandler.getCommandManager().save();
            }
        }
        return true;
    }
}