package dino.user;

/**
 * Custom exception class which catches all Dino-specific exceptions.
 */
public class DinoException extends Exception {
    public DinoException() {
        super(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DinoException(String message) {
        super(message);
    }
}
