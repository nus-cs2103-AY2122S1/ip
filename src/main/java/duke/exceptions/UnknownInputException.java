package duke.exceptions;

public class UnknownInputException extends DukeException {

    /**
     * Constructor for UnknownInputException that has a specific message for it
     */
    public UnknownInputException() {
        super("I don't know what that means :-(");
    }
}
