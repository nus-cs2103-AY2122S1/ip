package exceptions;

public class EmptyTaskListException extends DukeException {
    public EmptyTaskListException(String errorMessage) {
        super(errorMessage);
    }
}
