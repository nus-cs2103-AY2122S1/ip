package exception;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("THe list is currently empty!");
    }
}
