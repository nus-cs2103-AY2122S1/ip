package duke.exception;

/**
 * Class that encapsulates an unknown command Duke-specific exception.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructs an UnknownCommandException instance.
     */
    public UnknownCommandException() {
        super("I'm not too sure what you mean :(");
    }
}
