package duke.command;

import duke.exception.DukeException;

public abstract class Command {
    private String commandString;

    protected void setCommandString(String commandString) {
        this.commandString = commandString;
    }

    // returns the length of the command string +1 to account for the whitespace
    protected int getCommandLength() {
        return commandString.length() + 1;
    }

    public String getCommandString() {
        return commandString;
    }

    public abstract void parse(String input) throws DukeException;
}