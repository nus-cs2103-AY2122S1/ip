package Pix.exception;

/**
 * Triggers when there is an I/O error in the program.
 */
public class PixIOException extends PixException {
    @Override
    public String getMessage() {
        return "There is an I/O error!";
    }
}