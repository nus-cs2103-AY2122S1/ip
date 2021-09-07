package duke.exception;

/**
 * Exception for when no task is given after a "todo" command is given
 */
public class IllegalTaskException extends Exception {
    public IllegalTaskException(String message) {
        super(message);
    }
}