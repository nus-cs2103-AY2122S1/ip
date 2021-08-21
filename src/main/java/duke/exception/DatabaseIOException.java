package duke.exception;

/**
 * Thrown when there is an exception in file handling, creating or directory
 * access.
 */
public class DatabaseIOException extends DukeException {

    public DatabaseIOException(String message) {
        super(message);
    }

}
