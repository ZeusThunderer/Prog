package server.commands;

import common.exchange.CommandStatus;
import common.exchange.Request;
import common.exchange.Response;

public interface Command {
    CommandStatus whatNeeded();
    Response execute(Request request);
    String getName();
}
