package server.commands;

import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import server.utils.CollectionManager;

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
        try {
            if (!request.isEmpty()) throw new WrongArgumentException();
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена!");
            return new Response(CommandStatus.OK,  "Коллекция очищена!");
        } catch (WrongArgumentException exception) {
            System.err.println("Использование: '" + getName() + "'");
            return new Response(CommandStatus.OK,  "Использование: '" + getName() + "'");
        }
    }
}
