package commands;

import exception.CollectionIsEmptyException;
import exception.GroupNotFoundException;
import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.StudyGroup;
import utils.CollectionManager;

public class UpdateIdCommand extends GeneralCommand{
    private CollectionManager collectionManager;
    boolean needUpdated;
    public UpdateIdCommand(CollectionManager collectionManager ) {
        super("update id {element} ", " обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.UPDATE;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     * @param request
     */
    @Override
    public Response execute(Request request) {
        try {
            if (request.getObject() == null) throw new WrongArgumentException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt( (String) request.getObject());
            StudyGroup oldGroup = collectionManager.getById(id);
            if (oldGroup == null) throw new GroupNotFoundException();
           /* collectionManager.updateById(oldGroup);*/
            System.out.println("Группа успешна изменена!");
            return null;
        } catch (WrongArgumentException exception) {
            return new Response( CommandStatus.OK ,"Неверное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            return new Response( CommandStatus.OK ,"Коллекция пуста!");
        } catch (NumberFormatException exception) {
            return new Response( CommandStatus.OK ,"ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            return new Response( CommandStatus.OK ,"Группы с таким ID в коллекции нет!");
        }
    }
}
