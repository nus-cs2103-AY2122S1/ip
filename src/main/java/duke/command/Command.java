package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /** Content of the command */
    protected String content;
    /** Message generated */
    protected String message;
    /** Task involved in the command */
    protected Task task;

    /**
     * Constructor of the class `Command`.
     *
     * @param content The content of the command received.
     */
    public Command(String content) {
        this.content = content;
    }

    /**
     * Updates the message to be printed.
     *
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     * @throws DukeException If error occurs while executing the command.
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        this.message = this.content;
    }

    /**
     * Returns whether the program is still running.
     *
     * @return Whether the program is running.
     */
    public boolean isRunning() {
        return true;
    }

    /**
     * Returns the command's processed result as a string.
     *
     * @return String representation of the result of processing a command.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                this.message +
                "____________________________________________________________\n";
    }
}
