package duke;

/**
 * Class to Abstract the Exceptions thrown by the Duke Classes
 */
public class DukeException extends Exception {
    /**
     * Constructor for the Duke Exception class
     * @param message The message to be displayed if the error is caught
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Method to return the message of the Exception
     * @return The message of the Exception
     */
    @Override
    public String getMessage() {
        return "" + super.getMessage();
    }
}
