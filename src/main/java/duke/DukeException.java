package duke;

public class DukeException extends Exception {
    /**
     * Constructs a DukeException with given message.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Concats this exception message with another exception message.
     *
     * @param e The other Exception.
     * @return A DukeException containing both exception messages.
     */
    public DukeException concat(Exception e) {
        return new DukeException(this.getMessage() + " " + e.getMessage());
    }

    /**
     * The error message to be displayed.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return "(O_O;) Oh no!! " + getMessage();
    }
}
