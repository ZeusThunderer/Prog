package commands;

import java.util.ArrayList;
import java.util.List;

public class Commander {

    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exitCommand;
    private Command executeScriptCommand;
    private Command addIfMaxCommand;
    private Command removeGreaterCommand;
    private Command removeLowerCommand;
    private Command sumOfExStudents;
    private Command CountGreaterAdminCommand;
    private Command PrintSemesterCommand;

    public Commander(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand, Command updateCommand,
                          Command removeByIdCommand, Command clearCommand, Command saveCommand, Command exitCommand, Command executeScriptCommand,
                          Command addIfMaxCommand, Command removeGreaterCommand, Command removeLowerCommand, Command sumOfExStudents,
                          Command CountGreaterAdminCommand, Command PrintSemesterCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.exitCommand = exitCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.sumOfExStudents = sumOfExStudents;
        this.CountGreaterAdminCommand = CountGreaterAdminCommand;
        this.PrintSemesterCommand = PrintSemesterCommand;

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(exitCommand);
        commands.add(executeScriptCommand);
        commands.add(addIfMaxCommand);
        commands.add(removeGreaterCommand);
        commands.add(removeLowerCommand);
        commands.add(sumOfExStudents);
        commands.add(CountGreaterAdminCommand);
        commands.add(PrintSemesterCommand);
    }


    /**
     * @return List of manager's commands.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Prints that command is not found.
     * @param command Comand, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                System.out.println(command.getName() + command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument) {
        return addIfMaxCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLower(String argument) {
        return removeLowerCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean sumOfExStudents(String argument) {
        return sumOfExStudents.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean countGreaterAdmin(String argument) {
        return CountGreaterAdminCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean printSemester(String argument) {
        return PrintSemesterCommand.execute(argument);
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}

