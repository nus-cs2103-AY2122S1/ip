package duke;

/**
 * Class that throws exceptions specific to Jarvis
 */
public class DukeException extends Exception {

    protected String message;

    /**
     * Prints the received error message to user
     *
     * @param message The error message to be printed
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
        assert !this.message.equals("") : "Error message is empty!";
    }

    /**
     * Returns the error message
     *
     * @return the error message
     */
    @Override
    public String toString() {
        return message;
    }
}
