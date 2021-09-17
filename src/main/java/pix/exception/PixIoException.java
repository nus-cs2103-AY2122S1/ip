package pix.exception;

/**
 * Triggers when there is an I/O error in the program.
 */
public class PixIoException extends PixException {
    @Override
    public String getMessage() {
        return "There is an I/O error!";
    }
}
