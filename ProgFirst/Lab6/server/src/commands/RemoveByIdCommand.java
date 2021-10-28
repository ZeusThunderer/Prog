package commands;

import exception.CollectionIsEmptyException;
import exception.GroupNotFoundException;
import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.StudyGroup;
import utils.CollectionManager;

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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.valueOf((String) request.getObject());
            StudyGroup groupToRemove = collectionManager.getById(id);
            if (groupToRemove == null) throw new GroupNotFoundException();
            collectionManager.removeFromCollection(groupToRemove,request);
            System.out.println("Группа успешно удалена!");
            return new Response( CommandStatus.OK, "Группа успешно удалена!" );
        }catch (CollectionIsEmptyException exception) {
            return new Response( CommandStatus.OK ,"Коллекция пуста!");
        } catch (NumberFormatException exception) {
            return new Response( CommandStatus.OK ,"ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            return new Response( CommandStatus.OK ,"Группы с таким ID в коллекции нет!");
        }
    }
}
