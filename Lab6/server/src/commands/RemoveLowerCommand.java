package commands;

import exception.CollectionIsEmptyException;
import exception.GroupNotFoundException;
import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.RawGroup;
import stdgroup.StudyGroup;
import utils.CollectionManager;

/**
 * Command 'remove_lower'. Removes elements greater than user entered.
 */
public class RemoveLowerCommand extends GeneralCommand {
    private CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower {element} ", "удалить из коллекции все элементы, меньшие, чем заданный");
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.removeLower(request);
            return new Response( CommandStatus.OK, "Группы успешно удалены!");
        }  catch (CollectionIsEmptyException exception) {
            return new Response( CommandStatus.ERROR, "Коллекция пуста!");
        } catch (GroupNotFoundException exception) {
            return new Response( CommandStatus.ERROR, "Такой группы нет!");
        }
}
}

