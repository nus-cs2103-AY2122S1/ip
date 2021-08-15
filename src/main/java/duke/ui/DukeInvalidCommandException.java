package duke.ui;

/**
 * Represents an exception that occurs when an invalid command is used as an input for the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeInvalidCommandException extends IllegalArgumentException {
    /**
     * Constructs a DukeInvalidCommandException instance with the given error message.
     *
     * @param message The error message.
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
