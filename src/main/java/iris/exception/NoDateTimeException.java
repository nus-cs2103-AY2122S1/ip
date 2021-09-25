package iris.exception;

/**
 * This exception is thrown when
 * the date or time is missing in user input.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class NoDateTimeException extends IrisException {

    /**
     * Returns an error message to highlight that
     * the date or time is missing in user input.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("The Date/Time of a task cannot be empty.");
    }
}
