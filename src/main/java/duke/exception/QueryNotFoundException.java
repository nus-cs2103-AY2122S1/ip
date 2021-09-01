package duke.exception;

public class QueryNotFoundException extends DukeException {
    public QueryNotFoundException() {
        super("We are not sure what you are looking for. Please try again.");
    }
}
