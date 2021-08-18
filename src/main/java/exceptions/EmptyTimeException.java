package exceptions;

public class EmptyTimeException extends DukeException {
    public EmptyTimeException(String taskCategory) {
        super("☹ OOPS!!! The time of a " + taskCategory + " cannot be empty.");
    }
}
