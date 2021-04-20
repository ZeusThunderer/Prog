package commands;

import exception.CollectionIsEmptyException;
import exception.WrongArgumentException;
import stdgroup.StudyGroup;
import stdgroup.enums.Semester;
import utils.GroupCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintSemesterCommand extends GeneralCommand{
    private GroupCollection groupCollection;

    public PrintSemesterCommand(GroupCollection groupCollection) {
        super("print_field_ascending_semester_enum ", "вывести значения поля semesterEnum всех элементов в порядке возрастания");
        this.groupCollection = groupCollection;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            if (groupCollection.getStudyGroupSet().size() == 0) throw new CollectionIsEmptyException();
            List<Semester> enums = new ArrayList<>();
            for (StudyGroup studyGroup : groupCollection.getStudyGroupSet())
                enums.add(studyGroup.getSemesterEnum());
            Collections.sort(enums);
            System.out.println(enums);
            return true;
        } catch (WrongArgumentException exception) {
            System.err.println("Неверное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException e) {
            System.err.println("Колекция пуста");
        }
        return false;

    }
}
