package neo;

/**
 *  This class represents the Exceptions thrown by Duke.
 *  Consult comments for error codes.
 *
 * @author Ryan Tian Jun.
 */

public class NeoException extends Exception {
    private final String errorMessage;

    // -1 default, 0: WhiteSpace, 1: Empty description, 2: Delete/ mark as done range errors
    private int type = -1;

    public NeoException(String errorMessage) {
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
    public NeoException(String errorMessage, int type) {
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
        String highlight = "\n" + "-------------------------------------------" + "\n";
        if (type == 0) {
            return highlight + "The " + errorMessage
                    + " Command and Description must be separated by whitespace!" + highlight;
        } else if (type == 1) {
            return highlight + "The description of " + errorMessage + " cannot be empty!" + highlight;
        } else if (type == 2) {
            return highlight + errorMessage
                    + " Invalid Number, only numbers greater than 0 are accepted" + highlight;
        }
        return highlight + errorMessage + highlight;
    }
}
