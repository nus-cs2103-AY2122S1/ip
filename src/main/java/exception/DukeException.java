package main.java.exception;

/**
 * The DukeException Exception is the superclass of all exceptions to be thrown that are specific to the features
 * of the Duke Application.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class DukeException extends Exception {

    /**
     * Superclass of all exceptions that could be thrown by the Duke app.
     * @param message error message to be shown.
     */
    public DukeException(String message) {
        super(message);
    }
}
