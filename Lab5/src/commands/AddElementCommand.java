package commands;

import exception.IncorrectScriptException;
import exception.WrongArgumentException;
import utils.GroupCollection;
import utils.GroupEnter;

public class AddElementCommand extends GeneralCommand{
    private GroupEnter groupEnter;
    GroupCollection groupCollection;
    public AddElementCommand(GroupCollection groupCollection,GroupEnter groupEnter) {
        super("add {element} ", "добавить новый элемент в коллекцию");
        this.groupEnter = groupEnter;
        this.groupCollection = groupCollection;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            groupCollection.addToCollection(groupEnter.getStudyGroup(groupCollection.generateNextId()));
            System.out.println("Группа успешно добавлена");
            return true;
        } catch (IncorrectScriptException e) {
            System.err.println("Ошибка в скрипте");
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}
