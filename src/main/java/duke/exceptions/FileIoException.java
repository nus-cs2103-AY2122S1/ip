package duke.exceptions;

/**
 * {@code FileIOException} extends from {@code AuguryException}s.
 * Gets thrown when file reading/writing fails.
 */
public class FileIOException extends AuguryException {
    public FileIOException(String message) {
        super(message);
    }
}
