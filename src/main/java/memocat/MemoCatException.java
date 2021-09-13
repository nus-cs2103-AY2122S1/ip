package memocat;

/**
 * A class of Exception that can be thrown for invalid memocat behaviours.
 */
public class MemoCatException extends Exception {

    public MemoCatException() {
        super();
    }

    public MemoCatException(String message) {
        super(message);
    }
}
