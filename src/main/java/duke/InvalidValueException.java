package duke;

/**
 * Represents an error when the value is invalid.
 */
public class InvalidValueException extends DukeException {
    InvalidValueException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of InvalidValueException.
     *
     * @return The string representation of InvalidValueException.
     */
    @Override
    public String toString() {
        return "InvalidValueException: " + getMessage();
    }
}
