package duke.exceptions;

public class EmptyQueryException extends DukeException{
    public EmptyQueryException() {
        super("Please enter a query to find!");
    }
}
