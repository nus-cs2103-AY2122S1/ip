package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}