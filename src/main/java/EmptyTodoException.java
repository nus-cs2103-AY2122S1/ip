public class EmptyTodoException extends DukeException{
    public EmptyTodoException(String err) {
        super(err);
    }

    public EmptyTodoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
