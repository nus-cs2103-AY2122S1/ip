package duke.exceptions;

/**
 * Exception to display error made by using in regards to delete format
 */
public class InvalidDeleteFormatException extends DukeException {
    public InvalidDeleteFormatException(String explanation) {
        super(explanation);
    }
}
