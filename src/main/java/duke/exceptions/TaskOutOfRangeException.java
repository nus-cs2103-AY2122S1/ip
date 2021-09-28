package duke.exceptions;

/**
 * Represents an exception due to invalid task number input during
 * done and delete duke.commands.
 */
public class TaskOutOfRangeException extends DukeException {
    /**
     * Constructor for <code>TaskOutOfRangeException</code>
     *
     * @param maxIndex number of duke.tasks a user has
     */
    public TaskOutOfRangeException(int maxIndex) {
        super(maxIndex == 1
                ? "Oops! No such task exists! You only have one task.\n"
                : "Oops! No such task exists.\nPlease use a number from 1 to "
                + String.valueOf(maxIndex) + ".\n");
    }

    public TaskOutOfRangeException() {
        super("Oops! Please add a number behind the command.\n");
    }
}
