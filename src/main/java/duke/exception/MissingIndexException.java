package duke.exception;

/**
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
 *
 * @author Benedict Chua
 */
public class MissingIndexException extends DukeException {
    public MissingIndexException() {
        super("BAKA! You need to provide an index so I know which task you are referring to!");
    }
}

