package duke.io;

/**
 * A Command class to encapsulate command names and its given arguments.
 */
public class Command {
    /**
     * An enumeration that serves as an identifier of valid commands
     */
    public enum CommandName {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, DOWITHINPERIOD, DATE, FIND
    }

    protected CommandName command;
    protected String[] args;

    /**
     * Constructs a Command with an identifier accepting no arguments
     *
     * @param command An identifier for the command
     */
    public Command(CommandName command) {
        this.command = command;
        this.args = null;
    }

    /**
     * Constructs a Command with an identifier and arbitrary number of arguments stored as String
     *
     * @param command An identifier for the command
     * @param args Any number of arguments stored in String format
     */
    public Command(CommandName command, String[] args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Returns the command identifier.
     *
     * @return An enumeration identifying the command received from user
     */
    public CommandName getCommand() {
        return this.command;
    }

    /**
     * Returns the arguments received with this command
     *
     * @return An array containing the string arguments
     */
    public String[] getArgs() {
        return this.args;
    }

    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
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
