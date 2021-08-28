package duke.exception;

/**
 * Thrown to indicate that the chatbot has received an invalid command,
 * or unable to execute a given command.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
