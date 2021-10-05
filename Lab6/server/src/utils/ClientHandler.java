package utils;

import commands.CommandManager;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.Person;
import stdgroup.RawGroup;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final ExecutorService requestHandler = Executors.newCachedThreadPool();
    private final ExecutorService responseSender = Executors.newFixedThreadPool(10);

    private CommandManager commandManager;
    private AuthManager authManager = new AuthManager(commandManager.getCollectionManager().getDbManager());
    private ConnectionHandler connectionHandler;
    private volatile ObjectOutputStream clientWriter;
    private volatile ObjectInputStream clientReader;
    public ClientHandler(Socket socket, ConnectionHandler connectionHandler, CommandManager commandManager) throws IOException {
        this.socket = socket;
        try {
            clientWriter = new  ObjectOutputStream(socket.getOutputStream());
            clientReader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connectionHandler = connectionHandler;
        this.commandManager = commandManager;
    }

    public void run(){
        System.out.println(socket.getInetAddress().toString());
        login();
        while (processingStatus());
        stop();
    }

    private void login(){
        try {
            System.out.println( "Ожидание авторизации" );
            Response success;
            do {
                Request request = readRequest();
                request.getUser().setPassword(  authManager.hashPassword(request.getUser().getPassword())  );
                success = authManager.checkUser( request.getUser());
                connectionHandler.send( success , clientWriter );
            }   while (success.getCommandStatus() != CommandStatus.LOGGED);
        } catch (IOException exception) {
            System.err.println("Произошла ошибка при полученнии данных!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean processingStatus() {
        Request request = null;
        Response response = null;
        try {
            request = readRequest();
            System.out.println( request.getCommandType() );
            CommandStatus cmd = check( request );
            if (cmd == null) {
                response = handleRequest(request);
                sendResponse( response );
            } else if (cmd == CommandStatus.ERROR){
                sendResponse( new Response( CommandStatus.OK , "Несущетвующая команда, для списка команд введите help" ) );
            } else {
                sendResponse( new Response(cmd) );
                if (cmd == CommandStatus.NEED_GROUP || cmd == CommandStatus.NEED_PERSON) {
                    request = null;
                    request = (Request) connectionHandler.receive( clientReader );
                }
                else if (cmd == CommandStatus.UPDATE)
                    System.out.println("хух");
                sendResponse( handleRequest( request ) );
            }
            return true;
        } catch (IOException exception) {
            if (request == null) {
                System.err.println("Непредвиденный разрыв соединения с клиентом!");
            } else {
                System.out.println("Клиент успешно отключен от сервера!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
      /* Callable<Boolean> r = () -> {
            try {
                Response responseToUser;
                Request request = readRequest();
                System.out.println( request.getCommandType() );
                responseToUser = new Response( check( request ) );
                if (responseToUser.getCommandStatus() == CommandStatus.ERROR)
                    responseToUser = new Response( CommandStatus.ERROR , "Несущетвующая команда, для списка команд введите help" );
                else if (responseToUser.getCommandStatus() != null) {
                    responseToUser = handleRequest();
                    connectionHandler.send( responseToUser , clientWriter );
                } else {
                    responseToUser = handleRequest();
                    System.out.println( "Запрос '" + request.getCommandType() + "' успешно обработан." );
                }
                //responseToUser = handleRequest();
                connectionHandler.send( responseToUser , clientWriter );
                return true;
            } catch (InterruptedException e) {
                System.out.println("1");
            } catch (ExecutionException e) {
                System.out.println("2");
            } catch (IOException e) {
                System.out.println("3");
            } catch (ClassNotFoundException e) {
                System.out.println("4");
            }
        // };
       /* try {
            return requestHandler.submit(r).get();
        } catch (InterruptedException e) {
            System.out.println("4");
            return false;
        } catch (ExecutionException e) {
            System.out.println("5");
            return false;
        }*/
        return false;
    }


    public Request readRequest() throws IOException, ClassNotFoundException {
              Request request;
              request = (Request) connectionHandler.receive(clientReader );
              return request;
    }

    public CommandStatus check(Request request) {
        if (commandManager.getCommands().containsKey( request.getCommandType()))
            return commandManager.getCommands().get( request.getCommandType() ).whatNeeded();
        else return CommandStatus.ERROR;
    }

    public Response handleRequest(Request request) throws ExecutionException, InterruptedException{
            return requestHandler.submit( new RequestHandler( commandManager, request )).get();
    }
    public void sendResponse(Response response){
        Runnable r = () -> {
            try {
                connectionHandler.send( response, clientWriter);
            } catch (IOException e) {
                System.out.println("7");
            }
        };
        responseSender.submit( r );
    }

    private void stop() {
        try {
            socket.close();
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при завершении работы сервера!");
        }
    }
}
