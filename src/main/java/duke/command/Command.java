package duke.command;

public abstract class Command {
    protected String commandName;
    protected String description;

    public abstract void execute();
}
