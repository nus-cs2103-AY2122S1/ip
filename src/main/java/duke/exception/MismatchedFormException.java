package duke.exception;

/**
 * Deals with the informal input type.
 */
public class MismatchedFormException extends DukeException {
    public MismatchedFormException(String message, String requirement) {
        super("The content of " + message + " doesn't match the required form \"" + requirement + "\"");
    }
}
