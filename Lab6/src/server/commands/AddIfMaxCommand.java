package server.commands;

import common.exception.IncorrectScriptException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.StudyGroup;
import server.utils.CollectionManager;
import client.utils.GroupEnter;

public class AddIfMaxCommand extends GeneralCommand{
    CollectionManager collectionManager;
    public AddIfMaxCommand(CollectionManager collectionManager){
        super("add_if_max {element} ", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.NEED_GROUP;
    }

    @Override
    public Response execute(Request request) {
            Long id = (Long) request.getObject() ;
           /* StudyGroup group = groupEnter.getStudyGroup();
            if (collectionManager.collectionSize() == 0 || group.compareTo( collectionManager.getFirst()) < 0) {
                collectionManager.addToCollection(group);
                return new Response( CommandStatus.OK,  "Группа успешно добавлена!");
            } else return new Response( CommandStatus.OK,  "Значение группы меньше, чем значение наибольшего из солдат!");*/
        return new Response( CommandStatus.OK,  "Группа успешно добавлена!");
    }
}
