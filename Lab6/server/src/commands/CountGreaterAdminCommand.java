package commands;

import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.Person;
import stdgroup.StudyGroup;
import utils.CollectionManager;

public class CountGreaterAdminCommand extends GeneralCommand{
    CollectionManager collectionManager;
    public CountGreaterAdminCommand(CollectionManager collectionManager) {
        super("count_greater_than_group_admin groupAdmin ", "вывести количество групп, админы в которых младше чем введенный админ");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.NEED_PERSON;
    }
    @Override
    public Response execute(Request request) {
            int counter = 0;
           Person person = (Person) request.getObject();
           for (StudyGroup studyGroup : collectionManager.getStudyGroupSet())
                if (studyGroup.getGroupAdmin().compare(person))
                    counter++;
            return new Response( CommandStatus.OK, "Количество групп - " + counter );
    }
}
