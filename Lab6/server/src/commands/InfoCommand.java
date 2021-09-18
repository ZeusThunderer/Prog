package commands;

import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import utils.CollectionManager;

public class InfoCommand extends GeneralCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info ", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }


    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    /**
     * Executes the command.
     *
     * @return Command exit status.
     * @param request
     */
    @Override
    public Response execute(Request request) {
            return new Response( CommandStatus.OK,collectionManager.info());
    }
}
