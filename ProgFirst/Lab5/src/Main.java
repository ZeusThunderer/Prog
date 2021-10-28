
import commands.AddElementCommand;

import utils.FileManager;
import utils.GroupCollection;
import utils.GroupEnter;
import commands.*;
import utils.Output;

import java.io.BufferedInputStream;
import java.io.IOException;


public class Main {
    public static void main(String[] args){
        try (BufferedInputStream inputStream = new BufferedInputStream(System.in)) {
            final String envVariable = "JAVA_PATH";

            GroupEnter groupEnter = new GroupEnter(inputStream);
            FileManager fileManager = new FileManager(envVariable);
            GroupCollection groupCollection = new GroupCollection(fileManager);
            Commander commandManager = new Commander(
                    new HelpCommand(),
                    new InfoCommand(groupCollection),
                    new ShowCommand(groupCollection),
                    new AddElementCommand(groupCollection, groupEnter),
                    new UpdateIdCommand(groupCollection,groupEnter),
                    new RemoveByIdCommand(groupCollection),
                    new ClearCommand(groupCollection),
                    new SaveCommand(groupCollection),
                    new ExitCommand(),
                    new ExecuteScriptCommand(),
                    new AddIfMaxCommand(groupCollection, groupEnter),
                    new RemoveGreaterCommand(groupCollection, groupEnter),
                    new RemoveLowerCommand(groupCollection, groupEnter),
                    new SumOfExStudentsCommand(groupCollection),
                    new CountGreaterAdminCommand(groupCollection, groupEnter),
                    new PrintSemesterCommand(groupCollection)
            );
            Output output = new Output(commandManager, inputStream, groupEnter);

            output.interactiveMode();
        } catch (IOException e){}
        }
    }
