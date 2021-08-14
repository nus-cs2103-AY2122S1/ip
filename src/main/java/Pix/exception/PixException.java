package Pix.exception;

/**
 * The General Exception class that signals any errors.
 */
public class PixException extends Exception {
    public PixException() {

    }

    public PixException(String errorMessage) {
        super(errorMessage);
    }
}
