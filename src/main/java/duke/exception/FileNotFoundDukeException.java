package duke.exception;

/**
 * FileNotFoundDukeException class.
 * Used to represent an error in finding files.
 */
public class FileNotFoundDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! File not found.";

    /**
     * FileNotFoundDukeException constructor.
     */
    public FileNotFoundDukeException() {
        super(ERROR_MESSAGE);
    }
}
