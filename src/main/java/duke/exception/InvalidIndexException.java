package duke.exception;

/**
 * The InvalidIndexException is thrown when user inputs an index that is out of bounds of the TaskList.
 *
 * @author Benedict Chua
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int numOfTasks) {
        super(String.format("BAKA! Input a valid index!! You have %d tasks currently!", numOfTasks));
    }
}
