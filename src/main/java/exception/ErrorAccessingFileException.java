package exception;

/**
 * Encapsulates an exception when there is an error accessing a storage file.
 */
public class ErrorAccessingFileException extends DukeException {
    /**
     * Instantiates an exception when there is an error accessing a storage file.
     *
     * @param action Action performed on the storage file.
     */
    public ErrorAccessingFileException(String action) {
        super(String.format("Error accessing file when trying to %s", action));
    }
}
