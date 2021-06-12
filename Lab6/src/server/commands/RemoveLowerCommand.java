package server.commands;

import common.exception.CollectionIsEmptyException;
import common.exception.GroupNotFoundException;
import common.exception.IncorrectScriptException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.RawGroup;
import common.stdgroup.StudyGroup;
import server.utils.CollectionManager;
import client.utils.GroupEnter;

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
            if (!request.isEmpty()) throw new WrongArgumentException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            StudyGroup group =  new StudyGroup(collectionManager.generateNextId(),(RawGroup) request.getObject());
            StudyGroup groupByValue = collectionManager.getByValue(group);
            if (groupByValue == null) throw new GroupNotFoundException();
            collectionManager.removeLower(groupByValue);
            System.out.println("Группы успешно удалены!");
            return null;
        }  catch (CollectionIsEmptyException exception) {
            System.err.println("Коллекция пуста!");
        } catch (GroupNotFoundException exception) {
            System.err.println("Такой группы нет!");
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return null;
}
}

