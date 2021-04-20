package commands;

import exception.CollectionIsEmptyException;
import exception.GroupNotFoundException;
import exception.WrongArgumentException;
import stdgroup.StudyGroup;
import utils.GroupCollection;

public class RemoveByIdCommand extends GeneralCommand{
    private GroupCollection groupCollection;

    public RemoveByIdCommand(GroupCollection groupCollection) {
        super("remove_by_id <ID> ", "удалить элемент из коллекции по ID");
        this.groupCollection = groupCollection;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentException();
            if (groupCollection.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(argument);
            StudyGroup groupToRemove = groupCollection.getById(id);
            if (groupToRemove == null) throw new GroupNotFoundException();
            groupCollection.removeFromCollection(groupToRemove);
            System.out.println("Группа успешно удалена!");
            return true;
        }catch (CollectionIsEmptyException exception) {
           System.err.println("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            System.err.println("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            System.err.println("Группы с таким ID в коллекции нет!");
        } catch (WrongArgumentException exception) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}
