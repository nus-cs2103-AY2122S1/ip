package exception;

/**
 * The DescriptionException is thrown when a Task is to be added but is missing description.
 */
public class DescriptionException extends DukeException {

    /**
     * Constructs a new DescriptionException.
     *
     * @param task task which does not have a description.
     */
    public DescriptionException(String task) {
        super(String.format("The description of a %s cannot be empty.", task));
    }
}
