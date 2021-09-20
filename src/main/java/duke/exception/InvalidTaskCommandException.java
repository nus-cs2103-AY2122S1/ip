package duke.exception;

/**
 * Encapsulates the exception when the user enters an invalid task command.
 */
public class InvalidTaskCommandException extends DukeException {

    /**
     * Represents a constructor for the InvalidTaskCommandException class.
     */
    public InvalidTaskCommandException() {
        super("Your command is not in the right format! Please refer to the user guide to view" 
                + " the correct format for various types of task commands!");
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
