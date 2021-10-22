package duke.exception;

public class EmptyListException extends DukeException {

    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException() {
        super("No tasks in list!");
    }

}
