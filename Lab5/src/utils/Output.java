package utils;

import commands.Commander;
import exception.ScriptRecursionException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Output {
    private Commander commander;
    private BufferedInputStream stream;
    private GroupEnter groupEnter;
    private List<String> scriptStack = new ArrayList<>();

    public Output(Commander commander, BufferedInputStream stream, GroupEnter groupEnter) {
        this.commander = commander;
        this.stream = stream;
        this.groupEnter = groupEnter;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                System.out.print('>');
                userCommand[1] = "";
                String str = "";
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (str.contains(" "))
                    userCommand = str.split(" ", 2);
                else
                    userCommand[0] = str;
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            System.err.println("Пользовательский ввод не обнаружен!");
        }  catch (IOException e) {
            System.err.println("Непредвиденная ошибка!");
        }
    }

    /**
     * Mode for catching commands from a script.
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus=0;
        scriptStack.add(argument);
        try (BufferedInputStream scriptStream = new BufferedInputStream(new FileInputStream(new File(argument)))) {
            if (!(scriptStream.available() > 0)) throw new NoSuchElementException();
            BufferedInputStream inputStream = groupEnter.getStream();
            groupEnter.setStream(scriptStream);
            groupEnter.setFileMode();
            while (scriptStream.available()>0){
                userCommand[1] = "";
                String command = "";
                char c;
                while (true) {
                    c = (char) scriptStream.read();
                    if (c == '\n')
                        break;
                    command += c;
                }
                command.replace('\r',' ');
                command = command.trim();
                if (command.contains(" "))
                    userCommand = command.split(" ", 2);
                else
                    userCommand[0] = command;
                userCommand[1] = userCommand[1].trim();
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                System.out.println('>' + String.join(" ", userCommand));
                commandStatus = launchCommand(userCommand);
            }
            groupEnter.setStream(inputStream);
            groupEnter.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                System.out.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            System.err.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            System.err.println("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            System.err.println("Скрипты не могут вызываться рекурсивно!");
        } catch (IOException e) {

        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    /**
     * Launchs the command.
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commander.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commander.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commander.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commander.add(userCommand[1])) return 1;
                break;
            case "update_id":
                if (!commander.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commander.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commander.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commander.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commander.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "add_if_max":
                if (!commander.addIfMax(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if (!commander.removeGreater(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commander.removeLower(userCommand[1])) return 1;
                break;
            case "sum_of_expelled_students":
                if (!commander.sumOfExStudents(userCommand[1])) return 1;
                break;
            case "count_greater_than_group_admin":
                if (!commander.countGreaterAdmin(userCommand[1])) return 1;
                break;
            case "print_field_ascending_semester_enum":
                if (!commander.printSemester(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commander.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commander.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
}
