import commands.CommandManager;
import utils.ClientHandler;
import utils.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private int port;
    private int soTimeout;
    private ServerSocket serverSocket;
    private CommandManager commandManager;
    private ConnectionHandler connectionHandler;
    private final ExecutorService clientsThreads = Executors.newFixedThreadPool(5);

    public Server(int port, int soTimeout, CommandManager commandManager) {
        this.port = port;
        this.soTimeout = soTimeout;
        this.commandManager = commandManager;
    }

    /**
     * Begins server operation.
     */
    public void run() {
        openServerSocket();
        while (true){
                try {
                    clientsThreads.submit( new ClientHandler( connectToClient() , connectionHandler,commandManager ) );
                } catch (SocketTimeoutException e) {
                    System.out.println("Время ожидания подключения истекло");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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
            connectionHandler = new ConnectionHandler( serverSocket );
            System.out.println("порт " + port + " открыт");
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

}