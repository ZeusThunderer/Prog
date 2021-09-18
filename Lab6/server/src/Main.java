import utils.FileManager;
import utils.CollectionManager;
import commands.*;
import utils.RequestHandler;

import java.util.HashMap;



public class Main {
    public static void main(String[] args){

        final String envVariable = "JAVA_PATH";
        FileManager fileManager = new FileManager(envVariable);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(collectionManager);
        Server server  = new Server( 28910,20000 , new RequestHandler( commandManager ) );
        System.out.flush();
        server.run();
    }
}
