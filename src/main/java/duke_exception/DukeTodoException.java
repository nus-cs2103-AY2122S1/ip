package duke_exception;
public class DukeTodoException extends DukeException {
    public DukeTodoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
