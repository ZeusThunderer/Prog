package commands;

import exception.WrongArgumentException;

public class HelpCommand extends GeneralCommand{
    public HelpCommand() {
        super("help ", "вывести справку по доступным командам");
    }


    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            return true;
        } catch (WrongArgumentException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
