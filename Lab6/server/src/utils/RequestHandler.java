package utils;

import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;
import commands.CommandManager;

public class RequestHandler {
        private CommandManager commandManager;
        private CollectionManager manager;

        public RequestHandler(CommandManager commandManager) {
            this.commandManager = commandManager;
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

    public CommandStatus check(Request request) {
            if (commandManager.getCommands().containsKey( request.getCommandType()))
            return commandManager.getCommands().get( request.getCommandType() ).whatNeeded();
            else return CommandStatus.ERROR;
        }
    }
