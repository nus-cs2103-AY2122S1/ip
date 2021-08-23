package duke.exception;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Nee's list is empty! Add some tasks?");
    }
}
