package duke.exception;

/**
 * Encapsulates the main exception for Duke.
 */
public class DukeException extends Exception {

    /**
     * Represents a constructor for the DukeException class where the exception message is initialized.
     *
     * @param message Exception message that is shown to the user.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
