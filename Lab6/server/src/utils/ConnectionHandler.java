package utils;

import exchange.Request;
import exchange.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private ServerSocket serverSocket;
    public ConnectionHandler(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void run(){

    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    private Socket connectToClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }
    public Serializable receive(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Serializable request = (Serializable) objectInputStream.readObject();
        return request;
    }
    public void send(Response response, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.reset();
        objectOutputStream.writeObject(response);
    }
}
