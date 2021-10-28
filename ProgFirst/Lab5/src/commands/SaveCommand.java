package commands;

import exception.WrongArgumentException;
import utils.GroupCollection;

public class SaveCommand extends GeneralCommand{
    private GroupCollection groupCollection;

    public SaveCommand(GroupCollection groupCollection) {
        super("save ", "сохранить коллекцию в файл");
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
            groupCollection.saveCollection();
            return true;
        } catch (WrongArgumentException e) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}