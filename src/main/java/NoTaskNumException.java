/**
 * This exception is thrown when no task number is inputted.
 */
public class NoTaskNumException extends Exception {

    protected NoTaskNumException() {
        super("No task number inputted.");
    }
}