package commands;

import exception.WrongArgumentException;
import utils.GroupCollection;

public class ShowCommand extends GeneralCommand{
    private GroupCollection groupCollection;

    public ShowCommand(GroupCollection groupCollection) {
        super("show ", "вывести все элементы коллекции");
        this.groupCollection = groupCollection;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            System.out.println(groupCollection.toString());
            return true;
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}
