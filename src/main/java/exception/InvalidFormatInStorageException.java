package exception;

/**
 * Encapsulates an exception when there is data with an invalid format in storage.
 */
public class InvalidFormatInStorageException extends DukeException {
    /**
     * Instantiates an exception when there is data with an invalid format in storage.
     *
     * @param formatError Format that is invalid.
     * @param expectedFormat Expected format.
     */
    public InvalidFormatInStorageException(String formatError, String expectedFormat) {
        super(
                String.format("There was data with an invalid format in storage: %s\nExpected Format is: %s",
                formatError,
                expectedFormat)
        );
    }
}
