package utils;

import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import commands.CommandManager;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class RequestHandler implements Callable<Response> {
    private CommandManager commandManager;
    private Request request;

    public RequestHandler(CommandManager commandManager, Request request) {
        this.commandManager = commandManager;
        this.request = request;
    }

    /**
     * Handles requests.
     *
     * @param request Request to be processed.
     * @return Response to request.
     */
    public Response handle(Request request) {
        return commandManager.getCommands().get( request.getCommandType() ).execute( request );
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
    @Override
    public Response call() throws Exception {
        return commandManager.getCommands().get( request.getCommandType() ).execute( request );
    }
}
