package duke.exception;

/**
 * Exception thrown when something expected goes wrong within Duke
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Clears any queued message and prints the error message in red instead
     */
    public String displayError() {
        return "Oops! Something went wrong :(\n" + getMessage();
    }
}
