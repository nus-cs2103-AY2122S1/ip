package bot.commands;

import bot.error.DukeException;

/**
 * Represents a command.
 */
public class Command {
    protected static final String INFORM_FORMAT = "Now you have %d task(s) in the list.";
    protected static StringBuilder message;

    /**
     * Returns a String, the contents of which are to implemented by subclasses.
     *
     * @return A String to be shown to the user.
     */
    public String execute() throws DukeException {
        throw new DukeException("This is not a valid Command");
    }

    /**
     * Returns a boolean to determine whether the program can exit.
     *
     * @return A boolean to indicate end of the program.
     */
    public boolean canEnd() {
        return false;
    }
}
