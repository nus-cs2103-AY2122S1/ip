package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
