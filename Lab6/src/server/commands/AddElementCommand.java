package server.commands;

import common.exception.IncorrectScriptException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import common.stdgroup.StudyGroup;
import server.utils.CollectionManager;

public class AddElementCommand extends GeneralCommand{
    CollectionManager collectionManager;
    public AddElementCommand(CollectionManager collectionManager) {
        super("add {element} ", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return CommandStatus.NEED_GROUP;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (false) throw new WrongArgumentException();
            collectionManager.addToCollection(((StudyGroup) request.getObject()));
            return new Response( CommandStatus.OK, "Группа успешно добавлена" );
        } catch (WrongArgumentException e) {
            return new Response( CommandStatus.OK, "Неправильное использование: '" + getName() + "'" );
        }
    }
}
