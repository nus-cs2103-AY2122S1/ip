package Pix.exception;

/**
 * Triggers when the user inputs an unknown command.
 */
public class PixUnknownCommandException extends PixException {

    @Override
    public String getMessage() {
        return "(ಠ ∩ಠ) Unknown Command: Do you even know what you're typing..?";
    }
}
