package duke.exceptions;

public class EmptyDetailsException extends DukeExceptions {
    public EmptyDetailsException(String message) {
        super("Sorry! Please include more details for a " + message + " item :(\nPlease try again!");
    }
}
