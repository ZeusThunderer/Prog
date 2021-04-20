package commands;

import exception.WrongArgumentException;

public class ExecuteScriptCommand extends GeneralCommand{
    public ExecuteScriptCommand() {
        super("execute_script file_name ", "читать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    /**
     * Executes the command, but partially.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentException();
            System.out.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongArgumentException exception) {
            System.err.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
