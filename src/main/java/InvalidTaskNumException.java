/**
 * This exception is thrown when no task number is inputted.
 */
public class InvalidTaskNumException extends Exception {

    protected InvalidTaskNumException() {
        super("Invalid task number inputted.");
    }
}