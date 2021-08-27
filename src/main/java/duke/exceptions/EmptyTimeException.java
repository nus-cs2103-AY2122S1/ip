package duke.exceptions;

public class EmptyTimeException extends DukeException {
    /**
     * Creates exception to handle when user enters empty time
     *
     * @param taskCategory The Task category that user calls
     */
    public EmptyTimeException(String taskCategory) {
        super("☹ OOPS!!! The time of a " + taskCategory + " cannot be empty.");
    }
}
