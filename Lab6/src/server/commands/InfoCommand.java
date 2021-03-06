package server.commands;

import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import server.utils.CollectionManager;

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
        try {
            if (!request.isEmpty()) throw new WrongArgumentException();
            collectionManager.info();
            return null;
        } catch (WrongArgumentException exception) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return null;
    }
}
