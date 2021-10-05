package commands;

import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import stdgroup.StudyGroup;
import utils.CollectionManager;

public class GetByIDCommand extends GeneralCommand{
    private CollectionManager collectionManager;
    public GetByIDCommand(CollectionManager collectionManager) {
        super("get_by_id", "взять группу по id");
        this.collectionManager = collectionManager;
    }


    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     * @param request
     */
    @Override
    public Response execute(Request request) {
        try {
            if (request.getObject() == "") throw new WrongArgumentException();
            int id = Integer.valueOf( ((String) request.getObject()));
            for (StudyGroup studyGroup : collectionManager.getStudyGroupSet()) {
                if (studyGroup.getId() == id)
                    return new Response( CommandStatus.OK , collectionManager.getById( id ).toString() );
            }
            throw new WrongArgumentException();
        } catch (WrongArgumentException exception) {
            return new Response( CommandStatus.OK ,"Неверный id");
        }
    }
}

