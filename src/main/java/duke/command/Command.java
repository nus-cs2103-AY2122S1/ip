package duke.command;

import duke.exception.DukeException;

public abstract class Command {
    private String commandString;

    /**
     * Sets the command string of this command.
     *
     * @param commandString User Input's first word to execute this command
     */
    protected void setCommandString(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the length of the command string + 1 to account for the whitespace
     *
     * @return length of command string + 1 to account for the whitespace
     */
    protected int getCommandLength() {
        return commandString.length() + 1;
    }

    /**
     * Returns the command string of this command.
     *
     * @return User Input's first word to execute this command
     */
    public String getCommandString() {
        return commandString;
    }

    /**
     * Parse the user input based on the command's possible parameters
     *
     * @param input Full user input
     * @throws DukeException Any exception handled when executing the command
     */
    public abstract void parse(String input) throws DukeException;
}