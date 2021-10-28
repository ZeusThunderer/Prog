package commands;

import exception.WrongArgumentException;
import utils.GroupCollection;

public class ClearCommand extends GeneralCommand{
    private GroupCollection groupCollection;
    public ClearCommand(GroupCollection groupCollection) {
        super("clear ", "очистить коллекцию");
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
            groupCollection.clearCollection();
            System.out.println("Коллекция очищена!");
            return true;
        } catch (WrongArgumentException exception) {
            System.err.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
