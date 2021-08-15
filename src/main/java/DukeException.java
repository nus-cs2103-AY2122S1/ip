package main.java;

public class DukeException extends Exception {

    /**
     * Superclass of all exceptions that could be thrown by the Duke app
     * @param message error message to be shown to user
     */
    public DukeException(String message) {
        super(message);
    }
}
