package commands;

import exception.CollectionIsEmptyException;
import exception.GroupNotFoundException;
import exception.IncorrectScriptException;
import exception.WrongArgumentException;
import stdgroup.Coordinates;
import stdgroup.Person;
import stdgroup.StudyGroup;
import stdgroup.enums.Semester;
import utils.GroupCollection;
import utils.GroupEnter;

import java.io.Console;
import java.time.LocalDateTime;

public class UpdateIdCommand extends GeneralCommand{
    private GroupCollection groupCollection;
    private GroupEnter groupEnter;
    public UpdateIdCommand(GroupCollection groupCollection,GroupEnter groupEnter) {
        super("update id {element} ", " обновить значение элемента коллекции, id которого равен заданному");
        this.groupCollection = groupCollection;
        this.groupEnter = groupEnter;
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
            StudyGroup oldGroup = groupCollection.getById(id);
            if (oldGroup == null) throw new GroupNotFoundException();

            String name = oldGroup.getName();
            Coordinates coordinates = oldGroup.getCoordinates();
            LocalDateTime creationDate = oldGroup.getCreationDate();
            Long studensCount = oldGroup.getStudentsCount();
            int expelledStudents = oldGroup.getExpelledStudents();
            Float averageMark = oldGroup.getAverageMark();
            Semester semester = oldGroup.getSemesterEnum();
            Person groupAdmin = oldGroup.getGroupAdmin();

            groupCollection.removeFromCollection(oldGroup);

            if (groupEnter.change("Хотите изменить имя группы?")) name = groupEnter.getName();
            if (groupEnter.change("Хотите изменить координаты группы?")) coordinates = groupEnter.getCoordinates();
            if (groupEnter.change("Хотите изменить кол-во студентов?")) studensCount = groupEnter.getStudentsCount();
            if (groupEnter.change("Хотите изменить кол-во отчисленных студентов?")) expelledStudents = groupEnter.getExpelledStudents();
            if (groupEnter.change("Хотите изменить среднюю оценку")) averageMark = groupEnter.getAverageMark();
            if (groupEnter.change("Хотите изменить семестр?")) semester = groupEnter.getSemester();
            if (groupEnter.change("Хотите изменить админа группы?")) groupAdmin = groupEnter.getGroupAdmin();

            groupCollection.addToCollection(new StudyGroup(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    studensCount,
                    expelledStudents,
                    averageMark,
                    semester,
                    groupAdmin
            ));
            System.out.println("Группа успешна изменена!");
            return true;
        } catch (WrongArgumentException exception) {
            System.err.println("Неверное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            System.err.println("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            System.err.println("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            System.err.println("Группы с таким ID в коллекции нет!");
        } catch (IncorrectScriptException exception) {

        }
        return false;
    }
}
