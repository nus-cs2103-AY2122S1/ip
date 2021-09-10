package duke.exception;

public class IncorrectIndexException extends DukeException {

    /**
     * The constructor method for DukeException
     *
     */
    public IncorrectIndexException() {
        super("The given ID argument is incorrect.");
    }
}
