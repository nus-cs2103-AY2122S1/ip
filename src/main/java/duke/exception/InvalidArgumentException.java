package duke.exception;

/**
 * Signals that the given argument format is invalid when parsing a command, or creating a task.
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Constructs an InvalidArgumentException.
     *
     * @param numOfTask Number of task in tasks.
     */
    public InvalidArgumentException(int numOfTask) {
        super(numOfTask < 0
                ? "Please input a positive number."
                : numOfTask == 0
                ? "You do not have any tasks."
                : numOfTask == 1
                ? "You only have " + numOfTask + " task."
                : "You only have " + numOfTask + " tasks."
        );
    }
}
