package server;
import server.utils.FileManager;
import server.utils.CollectionManager;
import server.commands.*;
import server.utils.RequestHandler;

import java.util.HashMap;



public class Main {
    public static void main(String[] args){

        final String envVariable = "JAVA_PATH";
        FileManager fileManager = new FileManager(envVariable);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        HashMap<String, Command> commands = new HashMap<String, Command>();
        {
            commands.put( "add" , new AddElementCommand( collectionManager ) );
            commands.put( "add_if_max" , new AddIfMaxCommand( collectionManager ) );
            commands.put( "clear" , new ClearCommand( collectionManager ) );
            commands.put( "count_greater_than_group_admin" , new CountGreaterAdminCommand( collectionManager ) );
            commands.put( "help" , new HelpCommand( commands ) );
            commands.put( "info" , new InfoCommand( collectionManager ) );
            commands.put( "print_semester" , new PrintSemesterCommand( collectionManager ) );
            commands.put( "remove_by_id" , new RemoveByIdCommand( collectionManager ) );
            commands.put( "remove_greater" , new RemoveGreaterCommand( collectionManager ) );
            commands.put( "remove_lower" , new RemoveLowerCommand( collectionManager ) );
            commands.put( "save" , new SaveCommand( collectionManager ) );
            commands.put( "show" , new ShowCommand( collectionManager ) );
            commands.put( "sum_of_expelled_students" , new SumOfExStudentsCommand( collectionManager ) );
            commands.put( "update_by_id" , new UpdateIdCommand( collectionManager ) );
        }
        CommandManager commandManager = new CommandManager(commands);
        Server server  = new Server( 28910,20000 , new RequestHandler( commandManager ) );
        server.run();
    }
}
