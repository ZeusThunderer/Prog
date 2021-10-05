package commands;

import exchange.Request;
import utils.CollectionManager;

import java.util.HashMap;

public class CommandManager {

    private HashMap<String,Command> commands = new HashMap<>();
    private CollectionManager collectionManager;
    private SaveCommand saveCommand;
    public CommandManager(CollectionManager collectionManager) {
            this.collectionManager= collectionManager;
            initialiseCommand();
    }

    private void initialiseCommand() {
        {
            commands.put( "add" , new AddElementCommand( collectionManager ) );
            commands.put( "add_if_max" , new AddIfMaxCommand( collectionManager ) );
            commands.put( "clear" , new ClearCommand( collectionManager ) );
            commands.put( "count_greater_than_group_admin" , new CountGreaterAdminCommand( collectionManager ) );
            commands.put( "get_by_id", new GetByIDCommand( collectionManager ));
            commands.put( "help" , new HelpCommand( commands ) );
            commands.put( "info" , new InfoCommand( collectionManager ) );
            commands.put( "print_semester" , new PrintSemesterCommand( collectionManager ) );
            commands.put( "remove_by_id" , new RemoveByIdCommand( collectionManager ) );
            commands.put( "remove_greater" , new RemoveGreaterCommand( collectionManager ) );
            commands.put( "remove_lower" , new RemoveLowerCommand( collectionManager ) );
            commands.put( "show" , new ShowCommand( collectionManager ) );
            commands.put( "sum_of_expelled_students" , new SumOfExStudentsCommand( collectionManager ) );
            commands.put( "update_by_id" , new UpdateIdCommand( collectionManager ) );
        }
        saveCommand = new SaveCommand( collectionManager );
    }


    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    /**
     * @return List of manager's server.commands.
     */

    public HashMap<String,Command> getCommands() {
        return commands;
    }
    public void save(){
        saveCommand.execute( null);
    }
    public void executeCommand(Command command, Request request){
        command.execute(request);
    }
    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }


    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }

    public SaveCommand getSaveCommand() {
        return saveCommand;
    }
}

