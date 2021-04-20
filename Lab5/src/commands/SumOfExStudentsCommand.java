package commands;

import exception.CollectionIsEmptyException;
import exception.WrongArgumentException;
import utils.GroupCollection;

public class SumOfExStudentsCommand extends GeneralCommand{
    private GroupCollection groupCollection;
    public SumOfExStudentsCommand(GroupCollection groupCollection)
    {
        super("sum_of_expelled_students ", "sum_of_expelled_students");
        this.groupCollection = groupCollection;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            int sumStuds = groupCollection.getSumOfExStudends();
            if (sumStuds == 0) throw new CollectionIsEmptyException();
            System.out.println("Сумма всех отчисленных студентов: " + sumStuds);
            return true;
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            System.err.println("Коллекция пуста!");
        }
        return false;
    }
}
