package server.commands;

import common.exception.CollectionIsEmptyException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.StudyGroup;
import common.stdgroup.enums.Semester;
import server.utils.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintSemesterCommand extends GeneralCommand{
    private CollectionManager collectionManager;

    public PrintSemesterCommand(CollectionManager collectionManager) {
        super("print_field_ascending_semester_enum ", "вывести значения поля semesterEnum всех элементов в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    @Override
    public Response execute(Request request) {
        try {
            if (!request.isEmpty()) throw new WrongArgumentException();
            if (collectionManager.getStudyGroupSet().size() == 0) throw new CollectionIsEmptyException();
            List<Semester> enums = new ArrayList<>();
            for (StudyGroup studyGroup : collectionManager.getStudyGroupSet())
                enums.add(studyGroup.getSemesterEnum());
            Collections.sort(enums);
            System.out.println(enums);
            return null;        } catch (WrongArgumentException exception) {
            System.err.println("Неверное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException e) {
            System.err.println("Колекция пуста");
        }
        return null;
    }
}
