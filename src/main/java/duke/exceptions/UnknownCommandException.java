package duke.exceptions;

public class UnknownCommandException extends DukeException {

    /**
     * UnknownCommandException constructor.
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
