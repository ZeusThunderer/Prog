package server.commands;

import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import server.utils.CollectionManager;

public class ShowCommand extends GeneralCommand{
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show ", "вывести все элементы коллекции");
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
            if (request.isEmpty()) throw new WrongArgumentException();
            return new Response( CommandStatus.OK, collectionManager.toString());
        } catch (WrongArgumentException e) {
            return  new Response( CommandStatus.ERROR, "Неправильное использование: '" + getName() + "'");
        }
    }
}
