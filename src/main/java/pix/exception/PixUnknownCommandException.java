package pix.exception;

/**
 * Triggers when the user inputs an unknown command.
 */
public class PixUnknownCommandException extends PixException {
    @Override
    public String getMessage() {
        return "Unknown Command: Do you even know what you're typing..?";
    }
}
