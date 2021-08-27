package duke.io;

public class Command {
    public enum CommandName {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, DATE
    }

    protected CommandName command;
    protected String[] args;

    public Command(CommandName command) {
        this.command = command;
        this.args = null;
    }

    public Command(CommandName command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public CommandName getCommand() {
        return this.command;
    }

    public String[] getArgs() {
        return this.args;
    }
}
