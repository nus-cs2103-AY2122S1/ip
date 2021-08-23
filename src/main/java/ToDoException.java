public class ToDoException extends DukeException {
    public ToDoException() {
        super("The description of a todo cannot be empty.");
    }
}
