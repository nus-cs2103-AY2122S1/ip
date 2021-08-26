package duke.exceptions;

/**
 * Exception to display error made by using in regards to deadline format
 */
public class InvalidDeadlineFormatException extends DukeException {
    public InvalidDeadlineFormatException(String explanation){
        super(explanation);
    }
}
