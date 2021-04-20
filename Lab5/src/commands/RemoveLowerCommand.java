package commands;

import exception.CollectionIsEmptyException;
import exception.GroupNotFoundException;
import exception.IncorrectScriptException;
import exception.WrongArgumentException;
import stdgroup.StudyGroup;
import utils.GroupCollection;
import utils.GroupEnter;

/**
 * Command 'remove_lower'. Removes elements greater than user entered.
 */
public class RemoveLowerCommand extends GeneralCommand {
    private GroupCollection groupCollection;
    private GroupEnter groupEnter;

    public RemoveLowerCommand(GroupCollection collectionManager, GroupEnter marineAsker) {
        super("remove_lower {element} ", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.groupCollection = collectionManager;
        this.groupEnter = marineAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            if (groupCollection.collectionSize() == 0) throw new CollectionIsEmptyException();
            StudyGroup group = groupEnter.getStudyGroup(groupCollection.generateNextId());
            StudyGroup groupByValue = groupCollection.getByValue(group);
            if (groupByValue == null) throw new GroupNotFoundException();
            groupCollection.removeLower(groupByValue);
            System.out.println("Группы успешно удалены!");
            return true;
        }  catch (CollectionIsEmptyException exception) {
            System.err.println("Коллекция пуста!");
        } catch (GroupNotFoundException exception) {
            System.err.println("Такой группы нет!");
        } catch (IncorrectScriptException e) {
            System.err.println("Ошибка в скрипте");
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
}
}

