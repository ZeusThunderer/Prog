package commands;

import exception.CollectionIsEmptyException;
import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import utils.CollectionManager;

public class SumOfExStudentsCommand extends GeneralCommand{
    private CollectionManager collectionManager;
    public SumOfExStudentsCommand(CollectionManager collectionManager)
    {
        super("sum_of_expelled_students ", "sum_of_expelled_students");
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    @Override
    public Response execute(Request request) {
        try {
            if (collectionManager.getStudyGroupSet().isEmpty()) throw new CollectionIsEmptyException();
            int sumStuds = collectionManager.getSumOfExStudents();
            return new Response( CommandStatus.OK, "Сумма всех отчисленных студентов: " + sumStuds);
        } catch (CollectionIsEmptyException exception) {
            return new Response( CommandStatus.ERROR,"Коллекция пуста!");
        }
    }
}
