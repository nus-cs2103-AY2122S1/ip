package duke.exception;

/**
 * Encapsulates exceptions thrown when accessing databases.
 */
public class DatabaseAccessException extends DukeException {

    public DatabaseAccessException(String message) {
        super(message);
    }

}
