package commands;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import utils.CollectionManager;

public class ClearCommand extends GeneralCommand{
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager) {
        super("clear ", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }


    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     * @param request
     */
    @Override
    public Response execute(Request request) {
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена!");
            return new Response(CommandStatus.OK,  "Коллекция очищена!");
    }
}
