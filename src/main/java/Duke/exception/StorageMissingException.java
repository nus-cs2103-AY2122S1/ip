package duke.exception;

/**
 * Exception is thrown if storage is missing and cannot be created.
 */
public class StorageMissingException extends DukeException {

    /**
     * Constructor.
     *
     * @param str Exception message to be printed.
     */
    public StorageMissingException(String str) {
        super(str);
    }
}
