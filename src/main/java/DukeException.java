/**
 *  This class represents the Exceptions thrown by Duke
 *  Consult comments for error codes
 * @author Ryan Tian Jun
 */

public class DukeException extends Exception {
    private String message;

    // -1 default, 0: WhiteSpace, 1: Empty description, 2: Delete/ mark as done range errors
    private int type = -1;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
    public DukeException(String errorMessage, int type) {
        super(errorMessage);
        this.message = errorMessage;
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == 0) {
            return "The " + message + " Command and Description must be separated by whitespace!";
        } else if (type == 1) {
            return "The description of " + message + " cannot be empty!";
        } else if (type == 2) {
            return message + " Invalid Number, only numbers greater than 0 are accepted";
        }
        return message;
    }
}
