package server.commands;

import common.exception.IncorrectScriptException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.Person;
import common.stdgroup.StudyGroup;
import server.utils.CollectionManager;
import client.utils.GroupEnter;

public class CountGreaterAdminCommand extends GeneralCommand{
    CollectionManager collectionManager;
    public CountGreaterAdminCommand(CollectionManager collectionManager) {
        super("count_greater_than_group_admin groupAdmin ", "вывести количество элементов, значение поля groupAdmin которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.NEED_PERSON;
    }
    @Override
    public Response execute(Request request) {
        try {
            int counter = 0;
            if (!((String) request.getObject()).isEmpty()) throw new WrongArgumentException();
           Person person = (Person) request.getObject();
           for (StudyGroup studyGroup : collectionManager.getStudyGroupSet())
               if (studyGroup.getGroupAdmin().compare(person))
                   counter++;
           System.out.println("Количество элементов, значение поля groupAdmin которых больше заданного - " + counter);
            return null;
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return null;
    }
}
