package server.commands;

import common.exception.CollectionIsEmptyException;
import common.exception.GroupNotFoundException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.StudyGroup;
import server.utils.CollectionManager;

public class RemoveByIdCommand extends GeneralCommand{
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID> ", "удалить элемент из коллекции по ID");
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = (Long) request.getObject();
            StudyGroup groupToRemove = collectionManager.getById(id);
            if (groupToRemove == null) throw new GroupNotFoundException();
            collectionManager.removeFromCollection(groupToRemove);
            System.out.println("Группа успешно удалена!");
            return null;
        }catch (CollectionIsEmptyException exception) {
           System.err.println("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            System.err.println("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            System.err.println("Группы с таким ID в коллекции нет!");
        } catch (WrongArgumentException exception) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return null;
    }
}
