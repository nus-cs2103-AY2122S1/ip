package duke.exception;

/**
 * Class that encapsulates an invalid task number Duke-specific exception.
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Constructs a InvalidTaskNumberException instance.
     */
    public InvalidTaskNumberException() {
        super("Not a valid task number!");
    }
}
