package commands;

import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import utils.CollectionManager;

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