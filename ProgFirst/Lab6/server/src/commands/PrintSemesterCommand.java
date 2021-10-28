package commands;

import exception.CollectionIsEmptyException;
import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.StudyGroup;
import stdgroup.enums.Semester;
import utils.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintSemesterCommand extends GeneralCommand{
    private CollectionManager collectionManager;

    public PrintSemesterCommand(CollectionManager collectionManager) {
        super("print_semester", "вывести значения поля semesterEnum всех элементов в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    @Override
    public Response execute(Request request) {
        try {
            if (collectionManager.getStudyGroupSet().size() == 0) throw new CollectionIsEmptyException();
            List<Semester> enums = new ArrayList<>();
            for (StudyGroup studyGroup : collectionManager.getStudyGroupSet())
                enums.add(studyGroup.getSemesterEnum());
            Collections.sort(enums);
            return new Response( CommandStatus.OK, enums.toString() );
        } catch (CollectionIsEmptyException e) {
            return new Response( CommandStatus.OK ,"Колекция пуста");
        }
    }
}
