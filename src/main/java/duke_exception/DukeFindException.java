package duke_exception;

public class DukeFindException extends DukeException{
    public DukeFindException() {
        super("OOPS!!! The query of a find cannot be empty.");
    }
}
