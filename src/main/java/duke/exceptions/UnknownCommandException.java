package duke.exceptions;

/**
 * This is an duke.exceptions.UnknownCommandException that extends duke.exceptions.DukeException.
 */
public class UnknownCommandException extends DukeException {

    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what you mean ☹");
    }

}
