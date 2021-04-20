package commands;

import exception.WrongArgumentException;

public class ExitCommand extends GeneralCommand{
    public ExitCommand() {
        super("exit ", "завершить программу (без сохранения в файл)");
    }


    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            return true;
        } catch (WrongArgumentException exception) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}
