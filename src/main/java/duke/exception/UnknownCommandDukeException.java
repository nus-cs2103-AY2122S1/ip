package duke.exception;

/**
 * TaskNotFoundDukeException class.
 * Used to represent an unknown command input.
 */
public class UnknownCommandDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * UnknownCommandDukeException constructor.
     */
    public UnknownCommandDukeException() {
        super(ERROR_MESSAGE);
    }
}
