package duke.exception;

/**
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
 *
 * @author Benedict Chua
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int numOfTasks) {
        super(String.format("BAKA! Input a valid index!! You have %d tasks currently!", numOfTasks));
    }
}
