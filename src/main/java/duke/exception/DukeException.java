package duke.exception;

/**
 * Exception thrown when something expected goes wrong within Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a response string to be shown to the user.
     *
     * @return The response to be shown to the user with information about the error.
     */
    @Override
    public String getMessage() {
        return "Oops! Something went wrong :(\n" + super.getMessage();
    }
}
