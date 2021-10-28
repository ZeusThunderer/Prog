import utils.CollectionManager;
import commands.*;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        final String envVariable = "JAVA_PATH";
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        Server server  = new Server( 28910,120000 , commandManager);
        System.out.flush();
        server.run();
    }
}
