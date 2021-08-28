package pix.exception;

/**
 * Triggers when the format of the specified Pix.task is wrong.
 */
public class PixInvalidTaskException extends PixException {
    @Override
    public String getMessage() {
        return "The format to enter the specified Pix.task is wrong";
    }
}
