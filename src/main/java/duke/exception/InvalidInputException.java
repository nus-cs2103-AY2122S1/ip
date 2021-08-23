package duke.exception;

public class InvalidInputException extends DukeException{
    /**
     * A constructor for this InvalidInputException.
     */
    public InvalidInputException() {
        super("That is not a valid input!");
    }
}
