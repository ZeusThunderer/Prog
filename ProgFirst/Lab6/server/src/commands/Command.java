package commands;

import exchange.CommandStatus;
import exchange.Request;
import exchange.Response;

public interface Command {
    CommandStatus whatNeeded();
    Response execute(Request request);
    String getName();
}
