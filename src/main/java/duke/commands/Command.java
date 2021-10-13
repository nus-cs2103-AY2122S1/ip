package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * This class is the parent class of all commands.
 *
 * @author Kleon Ang
 */
public abstract class Command {
    protected final TaskList tasks;

    /**
     * Constructor for a Command.
     *
     * @param tasks A TaskList containing tasks to be accessed by the Command.
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the output reply string from the Command to be shown to the user.
     *
     * @param arguments Arguments for the Command.
     * @return A string containing the reply from the Command.
     * @throws DukeException A Duke-specific exception that may occur when executing the Command.
     */
    public abstract String getReply(String arguments) throws DukeException;
}
