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
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends GeneralCommand {
    private CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("remove_greater {element} ", "удалить из коллекции все элементы, превышающие заданный");
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            StudyGroup group =  new StudyGroup(collectionManager.generateNextId(),(RawGroup) request.getObject());
            StudyGroup groupByValue = collectionManager.getByValue(group);
            if (groupByValue == null) throw new GroupNotFoundException();
            collectionManager.removeGreater(groupByValue);
            return new Response( CommandStatus.OK, "Группы успешно удалены!");
        }  catch (CollectionIsEmptyException exception) {
            return new Response( CommandStatus.ERROR, "Коллекция пуста!");
        } catch (GroupNotFoundException exception) {
            return new Response( CommandStatus.ERROR, "Такой группы нет!");
        } catch (WrongArgumentException e) {
            return new Response( CommandStatus.ERROR, "Неправильное использование: '" + getName() + "'");
        }
    }
}
