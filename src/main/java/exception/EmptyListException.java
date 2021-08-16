package exception;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("The list is currently empty!");
    }
}
