package duke.exception;

/**
 * Exception of Duke that occurs when attempting to access an index out of bounds of the task list.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int start, int end, int actual) {
        super("You entered an invalid task number of " + actual + ". Please enter a task number from " + start + " to " + end + ".");
    }
}
