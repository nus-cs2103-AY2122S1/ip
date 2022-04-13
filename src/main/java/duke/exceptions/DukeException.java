package duke.exceptions;

/**
 * Indicates conditions specific to Duke that should be caught.
 */
public class DukeException extends Exception {
    /**
     * Creates a duke exception with a detail message.
     *
     * @param message The specified detail message.
     */
    public DukeException(String message) {
        super("I'm sorry! " + message);
    }

    @Override
    public String toString() {
        return "I'm sorry! " + super.getMessage();
    }
}
