package duke.exceptions;

/**
 * {@code InvalidTaskCreationException} extends from {@code AuguryException}s.
 * Gets thrown when invalid parameters are passed during {@code Task} creation.
 */
public class InvalidTaskCreationException extends AuguryException {
    public InvalidTaskCreationException(String message) {
        super(message);
    }
}
