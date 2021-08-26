package duke.exceptions;

/**
 * Exception to display error made by using in regards to event format
 */
public class InvalidEventFormatException extends DukeException {
    public InvalidEventFormatException (String explanation) {
        super(explanation);
    }
}
