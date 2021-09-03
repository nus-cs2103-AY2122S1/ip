package duke;

/**
 * This class extends InvalidCommandException. It handles cases when users do not
 * specify activity description of the task they wish to add.
 */
public class EmptyDescriptionError extends InvalidCommandException {

    /**
     * Constructor for EmptyDescriptionError
     *
     * @param message The message will be either deadline, event, or todo.
     */
    public EmptyDescriptionError(String message) {
        super(String.format("The description of a %s cannot be empty.", message));
    }
}