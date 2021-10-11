package duke;

/**
 * The DukeException class encapsulates checked exceptions that occur specific to Duke.
 *
 * @author cai
 */
public class DukeException extends Exception {
    /**
     * Constructs a new Duke exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a new Duke exception with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
