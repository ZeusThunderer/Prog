package server.commands;

import common.exception.CollectionIsEmptyException;
import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;
import server.utils.CollectionManager;

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
            if (!request.isEmpty()) throw new WrongArgumentException();
            if (collectionManager.getStudyGroupSet().isEmpty()) throw new CollectionIsEmptyException();
            int sumStuds = collectionManager.getSumOfExStudents();
            return new Response( CommandStatus.OK, "Сумма всех отчисленных студентов: " + sumStuds);
        } catch (WrongArgumentException e) {
            return new Response( CommandStatus.ERROR,"Неправильное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            return new Response( CommandStatus.ERROR,"Коллекция пуста!");
        }
    }
}
