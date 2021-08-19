package exceptions;

/**
 * Represents an exception due to the user leaving
 * the task description empty.
 */
public class EmptyDescriptionException extends UserInputException {

    /**
     * Constructor for <code>EmptyDescriptionException</code>
     *
     * @param message message describing the exception that will be displayed to users
     */
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of a task cannot be empty.");
    }

}
