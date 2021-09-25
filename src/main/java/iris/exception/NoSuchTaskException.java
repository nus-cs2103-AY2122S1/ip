package iris.exception;

/**
 * This exception is thrown when
 * user inputs a non-existent task number
 * when executing deletion or marking of tasks.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class NoSuchTaskException extends IrisException {

    /**
     * Returns an error message to highlight that
     * the input task number does not exist.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("The task does not exist.");
    }
}
