package iris.exception;

/**
 * This exception is thrown when there is
 * no command to undo.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class NoPreviousCommandException extends IrisException {

    /**
     * Returns an error message to highlight that
     * there is no command to undo.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("There are no commands to undo.");
    }
}
