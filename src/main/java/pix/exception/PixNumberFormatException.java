package pix.exception;

/**
 * Triggers when there is a number format exception.
 */
public class PixNumberFormatException extends PixException {
    @Override
    public String getMessage() {
        return "Put the number as an integer!";
    }
}
