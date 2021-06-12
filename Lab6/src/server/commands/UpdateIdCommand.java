package server.commands;

import common.exception.CollectionIsEmptyException;
import common.exception.GroupNotFoundException;
import common.exception.IncorrectScriptException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.StudyGroup;
import server.utils.CollectionManager;
import client.utils.GroupEnter;

public class UpdateIdCommand extends GeneralCommand{
    private CollectionManager collectionManager;
    public UpdateIdCommand(CollectionManager collectionManager ) {
        super("update id {element} ", " обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.NEED_GROUP;
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong( (String) request.getObject());
            StudyGroup oldGroup = collectionManager.getById(id);
            if (oldGroup == null) throw new GroupNotFoundException();
           /* collectionManager.updateById(oldGroup);*/
            System.out.println("Группа успешна изменена!");
            return null;
        } catch (WrongArgumentException exception) {
            System.err.println("Неверное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            System.err.println("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            System.err.println("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            System.err.println("Группы с таким ID в коллекции нет!");
        }
        return null;
    }
}
