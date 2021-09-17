package exceptions;

/**
 * Abstract class for all other Duke Exceptions to extend from.
 *
 * @author Quan Teng Foong
 */
public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
