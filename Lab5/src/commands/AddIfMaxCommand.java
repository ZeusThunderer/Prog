package commands;

import exception.IncorrectScriptException;
import exception.WrongArgumentException;
import stdgroup.StudyGroup;
import utils.GroupCollection;
import utils.GroupEnter;

public class AddIfMaxCommand extends GeneralCommand{
    private GroupEnter groupEnter;
    GroupCollection groupCollection;
    public AddIfMaxCommand(GroupCollection groupCollection,GroupEnter groupEnter){
        super("add_if_max {element} ", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.groupCollection = groupCollection;
        this.groupEnter = groupEnter;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentException();
            Long id = Long.parseLong(argument);
            StudyGroup group = groupEnter.getStudyGroup(groupCollection.generateNextId());
            System.out.println("Группа успешно добавлена");
            if (groupCollection.collectionSize() == 0 || group.compareTo(groupCollection.getFirst()) < 0) {
                groupCollection.addToCollection(group);
                System.out.println("Солдат успешно добавлен!");
                return true;
            } else System.out.println("Значение группы меньше, чем значение наибольшего из солдат!");
        } catch (IncorrectScriptException e) {
            System.err.println("Ошибка в скрипте");
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}
