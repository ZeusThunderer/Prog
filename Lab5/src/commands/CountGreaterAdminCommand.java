package commands;

import exception.IncorrectScriptException;
import exception.WrongArgumentException;
import stdgroup.Person;
import stdgroup.StudyGroup;
import utils.GroupCollection;
import utils.GroupEnter;

public class CountGreaterAdminCommand extends GeneralCommand{
    private GroupEnter groupEnter;
    GroupCollection groupCollection;
    public CountGreaterAdminCommand(GroupCollection groupCollection,GroupEnter groupEnter) {
        super("count_greater_than_group_admin groupAdmin ", "вывести количество элементов, значение поля groupAdmin которых больше заданного");
        this.groupCollection = groupCollection;
        this.groupEnter = groupEnter;
    }

    @Override
    public boolean execute(String argument) {
        try {
            int counter = 0;
            if (argument.isEmpty()) throw new WrongArgumentException();
           Person person = groupEnter.getGroupAdmin();
           for (StudyGroup studyGroup : groupCollection.getStudyGroupSet())
               if (studyGroup.getGroupAdmin().compare(person))
                   counter++;
           System.out.println("Количество элементов, значение поля groupAdmin которых больше заданного - " + counter);
           return true;
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        } catch (IncorrectScriptException e) {
            System.err.println("Ошибка в скрипте");
        }
        return false;
    }
}
