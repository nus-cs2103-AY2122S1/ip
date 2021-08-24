package duke.exception;

/**
 * A subclass of DukeException. Thrown when users do not give a description to the task.
 *
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructor of an exception.
     * @param input User input used to display error message.
     */
    public EmptyDescriptionException(String input) {
        super("☹ OOPS!!! The description of " + (input.startsWith("e") ? "an " : "a ")
                + input + " cannot be empty.");
    }
}
