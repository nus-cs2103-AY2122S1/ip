package duke.exceptions;

public class EmptyDescriptionException extends DukeException {
    /**
     * Creates exception for the case of user entering empty description
     *
     * @param taskCategory The task category that user calls
     */
    public EmptyDescriptionException(String taskCategory) {
        super("☹ OOPS!!! The description of a " + taskCategory + " cannot be empty.");
    }
}
