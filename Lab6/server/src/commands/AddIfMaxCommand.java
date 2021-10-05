package commands;

import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.RawGroup;
import stdgroup.StudyGroup;
import utils.CollectionManager;

public class AddIfMaxCommand extends GeneralCommand{
    CollectionManager collectionManager;
    public AddIfMaxCommand(CollectionManager collectionManager){
        super("add_if_max {element} ", "добавить новый элемент в коллекцию, если количество студентов в этой группе превышает наибольшее значение в этой коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.NEED_GROUP;
    }

    @Override
    public Response execute(Request request) {
            long max = 0;
            RawGroup group = (RawGroup) request.getObject();
            if (collectionManager.collectionSize() == 0) {
                collectionManager.addToCollection(request);
                return new Response( CommandStatus.OK,  "Группа успешно добавлена!");
            }
            else {
                for (StudyGroup studyGroup : collectionManager.getStudyGroupSet()) {
                    if (studyGroup.getStudentsCount() > max)
                        max = studyGroup.getStudentsCount();
                }
                if (group.getStudentsCount() > max) {
                    collectionManager.addToCollection( request );
                    return new Response( CommandStatus.OK , "Группа успешно добавлена!" );
                }
                else
                    return new Response( CommandStatus.OK , "Значение группы меньше, чем значение наибольшей из коллекции !" );
            }
    }
}
