package duke.exceptions;

public class DukeTodoTimeException extends DukeException {

    public DukeTodoTimeException() {
        super("OOPS!!! Todo does not require time, only updating the description");
    }
}
