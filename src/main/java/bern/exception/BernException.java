package bern.exception;

/**
 * A parent class for all exceptions bern.Bern(this programme) related.
 */
public class BernException extends Exception {
    /**
     * Constructor for BernException.
     *
     * @param s The message passed to the instance of the exception.
     */
    public BernException(String s) {
        super(s);
    }
}
