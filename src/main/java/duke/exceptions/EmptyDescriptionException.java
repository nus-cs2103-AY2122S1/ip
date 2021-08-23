package duke.exceptions;

public class EmptyDescriptionException extends DukeExceptions {
    public EmptyDescriptionException(String message) {
        super("Sorry! There needs to be a description for a " + message + " item :(\nPlease try again!");
    }
}
