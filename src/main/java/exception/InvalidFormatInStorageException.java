package exception;

/**
 * Encapsulates an exception when there is data with an invalid format in storage.
 */
public class InvalidFormatInStorageException extends DukeException {
    /**
     * Instantiates an exception when there is data with an invalid format in storage.
     *
     * @param formatError format that is invalid.
     */
    public InvalidFormatInStorageException(String formatError) {
        super(String.format("There was data with an invalid format in storage: %s", formatError));
    }
}
