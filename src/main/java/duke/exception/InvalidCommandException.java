package duke.exception;

/**
 * Representation for the invalid command exception.
 */
public class InvalidCommandException extends DukeException {
    private static String LINE = "____________________________________________________________";
    private static String MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructor for InvalidCommandException.
     */
    public InvalidCommandException() {
            super(LINE + "\n" + MESSAGE + "\n" + LINE);
    }
}
