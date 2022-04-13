package duke.exception;

/**
 * Exception of Duke that occurs when attempting to access an index out of bounds of the task list.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructor of InvalidIndexException.
     *
     * @param start starting index of task list.
     * @param end ending index of task list.
     * @param actual index user inputted.
     */
    public InvalidIndexException(int start, int end, int actual) {
        super("You entered an invalid task number of " + actual
                + ". Please enter a task number from " + start + " to " + end + ".");
    }
}
