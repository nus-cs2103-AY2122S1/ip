package duke.exceptions;

/**
 * {@code FileIoException} extends from {@code AuguryException}s.
 * Gets thrown when file reading/writing fails.
 */
public class FileIoException extends AuguryException {
    public FileIoException(String message) {
        super(message);
    }
}
