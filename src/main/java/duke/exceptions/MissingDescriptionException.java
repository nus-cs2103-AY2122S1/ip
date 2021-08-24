package duke.exceptions;

public class MissingDescriptionException extends DukeException{
    public MissingDescriptionException(String message) {
        super(message);
    }

    public MissingDescriptionException() { }

    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.";
    }
}
