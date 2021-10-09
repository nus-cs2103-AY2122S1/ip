package duke.exception;

/**
 * Encapsulates the exception when the user doesn't provide any description for the command.
 */
public class EmptyDescriptionException extends DukeException {
    
    /**
     * Represents a constructor for the EmptyDescriptionException class.
     */
    public EmptyDescriptionException() {
        super("OOPS!!! The description of this command cannot be empty. Refer to the user guide to view " +
                "the correct format of this command!");
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
