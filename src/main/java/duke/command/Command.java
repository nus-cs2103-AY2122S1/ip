package duke.command;

public abstract class Command {
    /**
     * Returns a response from a given command type.
     *
     * @return The command responses.
     */
    public abstract String execute();
}
