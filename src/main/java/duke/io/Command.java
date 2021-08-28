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

    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Command)) {
            return false;
        } else {
            final Command otherCommand = (Command) otherObj;

            if (!this.command.equals(otherCommand.command)) {
                return false;
            } else if (this.args == null && otherCommand.args == null) {
                return true;
            } else if (this.args == null || otherCommand.args == null) {
                return false;
            } else if (this.args.length != otherCommand.args.length) {
                return false;
            } else {
                for (int i = 0; i < this.args.length; ++i) {
                    if (!this.args[i].equals(otherCommand.args[i])) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
