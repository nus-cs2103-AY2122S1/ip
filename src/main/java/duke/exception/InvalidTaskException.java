package duke.exception;

/**
 * Represents an exception thrown when task is in incorrect format.
 */
public class InvalidTaskException extends DukeException {

    /**
     * Initialise constructor for {@code InvalidTaskException}.
     */
    public InvalidTaskException() {
        super("Nee doesn't know what task you've done! "
                + "\nAdd a description in the correct format.");
    }
}
