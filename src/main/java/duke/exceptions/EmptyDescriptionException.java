package duke.exceptions;

/**
 * Represents an exception due to the user leaving
 * the task description empty.
 */
public class EmptyDescriptionException extends DukeException {

    /** Constructor for <code>EmptyDescriptionException</code> */
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.\n*sad quack*\n");
    }

}
