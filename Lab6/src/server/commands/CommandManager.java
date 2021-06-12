package server.commands;

import common.exchange.Request;

import java.util.HashMap;

public class CommandManager {

    private HashMap<String,Command> commands = new HashMap<>();
    public CommandManager(HashMap<String,Command> commands) {
            this.commands= commands;
    }


    /**
     * @return List of manager's server.commands.
     */
    public HashMap<String,Command> getCommands() {
        return commands;
    }

    public void executeCommand(Command command, Request request){
        command.execute(request);
    }
    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }


    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}

