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

