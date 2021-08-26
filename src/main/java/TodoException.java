public class TodoException extends DukeException1 {
    TodoException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a todo cannot be empty.";
    }
}
