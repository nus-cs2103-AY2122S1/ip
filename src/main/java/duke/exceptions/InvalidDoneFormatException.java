package duke.exceptions;

/**
 * Exception to display error made by using in regards to done format
 */
public class InvalidDoneFormatException extends DukeException {
    public InvalidDoneFormatException(String explanation){
        super(explanation);
    }
}
