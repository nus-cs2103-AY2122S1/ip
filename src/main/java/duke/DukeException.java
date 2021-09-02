package duke;

/**
 *  This class represents the Exceptions thrown by Duke.
 *  Consult comments for error codes.
 *
 * @author Ryan Tian Jun.
 */

public class DukeException extends Exception {
    private final String errorMessage;

    // -1 default, 0: WhiteSpace, 1: Empty description, 2: Delete/ mark as done range errors
    private int type = -1;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * This constructor is used for more general exceptions.
     * Refer to error codes for more details.
     *
     * @param errorMessage Error Message to be Displayed.
     * @param type type of the error, denoted by comment above.
     */
    public DukeException(String errorMessage, int type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

    /**
     * Returns the String representation of a DukeException.
     * Contains some general exceptions applied to across the program.
     * Error codes can be found above.
     *
     * @return returns the String representation of a DukeException.
     */
    @Override
    public String toString() {
        if (type == 0) {
            return "The " + errorMessage + " Command and Description must be separated by whitespace!";
        } else if (type == 1) {
            return "The description of " + errorMessage + " cannot be empty!";
        } else if (type == 2) {
            return errorMessage + " Invalid Number, only numbers greater than 0 are accepted";
        }
        return errorMessage;
    }
}
