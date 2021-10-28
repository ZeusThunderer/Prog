package commands;

import exception.WrongArgumentException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;

import java.util.HashMap;

public class HelpCommand extends GeneralCommand{
    private HashMap<String,Command> commands = new HashMap<>();
    public HelpCommand(HashMap<String, Command> commands)
    {
        super("help ", "вывести справку по доступным командам");
        this.commands=commands;
    }


    @Override
    public CommandStatus whatNeeded() {
        return null;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     * @param request
     */
    @Override
    public Response execute(Request request) {
            Response response = new Response( CommandStatus.OK,"" );
            commands.forEach((name,command) -> {
                response.addResponse(command.toString());
            });
            return  response;
    }
}
