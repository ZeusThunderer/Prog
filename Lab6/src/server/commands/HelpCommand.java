package server.commands;

import common.exception.WrongArgumentException;
import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;

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
        try {
            Response response = new Response( CommandStatus.OK,"" );
            if (request.isEmpty()) throw new WrongArgumentException();
            commands.forEach((name,command) -> {
                response.addResponse(command.toString());
            });
            return  response;
        } catch (WrongArgumentException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return null;
    }
}
