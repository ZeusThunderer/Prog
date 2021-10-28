package commands;

import java.util.Objects;

/**
 * Abstract class for commands
 */
public abstract class GeneralCommand implements Command {
    private String name;
    private String description;

    public GeneralCommand(String name, String description){
        this.name = name;
        this.description = description;
    }


    /**
     *
     * @return command name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return command description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralCommand that = (GeneralCommand) o;
        return name.equals(that.name) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
