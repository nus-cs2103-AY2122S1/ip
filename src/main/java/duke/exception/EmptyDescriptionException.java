package duke.exception;

/**
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
 *
 * @author Benedict Chua
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("BAKA! The description of a task cannot be empty!");
    }
}

