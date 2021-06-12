package server.commands;

import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import server.utils.CollectionManager;

public class SaveCommand extends GeneralCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save ", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.ERROR;
    }

    /**
     * Executes the command.
     *
     * @param request
     * @return Command exit status.
     */
    @Override
    public Response execute(Request request) {
        collectionManager.saveCollection();
        return null;
    }
}