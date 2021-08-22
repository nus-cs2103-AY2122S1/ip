package duke.exceptions;

/**
 * This is an UnknownCommandException that extends DukeException.
 * This exception is thrown when the user keys in random unrecognized commands.
 */
public class UnknownCommandException extends DukeException {

    /**
     * This is the UnknownCommandException constructor.
     */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what you mean ☹");
    }

}
