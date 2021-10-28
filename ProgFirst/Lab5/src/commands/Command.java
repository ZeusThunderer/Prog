package commands;

public interface Command {
    boolean execute(String argument);

    String getName();

    String getDescription();
}
