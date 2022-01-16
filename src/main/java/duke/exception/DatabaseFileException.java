package duke.exception;

/**
 * Thrown when there is an exception in file handling, creating or directory
 * access.
 */
public class DatabaseFileException extends DukeException {

    public DatabaseFileException(String message) {
        super(message);
    }

}
