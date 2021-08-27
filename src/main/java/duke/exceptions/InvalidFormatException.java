package duke.exceptions;

/**
 * Exception to display error made by using in regards to deadline format
 */
public class InvalidFormatException extends DukeException {
    public InvalidFormatException(String explanation){
        super(explanation);
    }
}
