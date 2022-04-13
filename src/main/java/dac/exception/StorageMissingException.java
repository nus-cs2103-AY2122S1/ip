package dac.exception;

/**
 * Exception is thrown if storage is missing and cannot be created.
 */
public class StorageMissingException extends DukeException {

    private String message;

    /**
     * Constructor.
     *
     * @param str Exception message to be printed.
     */
    public StorageMissingException(String str) {
        super(str);
        message = str;
    }

    /**
     * Returns a String representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return "Invalid instruction: " + message + " is not a valid instruction.";
    }
}
