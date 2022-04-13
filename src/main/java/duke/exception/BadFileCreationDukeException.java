package duke.exception;

/**
 * BadFileCreationDukeException class.
 * Used to represent an error in creating missing files.
 */
public class BadFileCreationDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! I can't seem to create the missing files.";

    /**
     * BadFileCreationDukeException constructor.
     */
    public BadFileCreationDukeException() {
        super(ERROR_MESSAGE);
    }
}
