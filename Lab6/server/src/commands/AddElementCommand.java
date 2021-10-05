package commands;

import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.RawGroup;
import stdgroup.StudyGroup;
import utils.CollectionManager;

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
            collectionManager.addToCollection(request);
            return new Response( CommandStatus.OK, "Группа успешно добавлена" );
    }
}
