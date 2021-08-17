public class ToDoException extends HAL9000Exception{
    public ToDoException() {
        super("The description of a todo cannot be empty.");
    }
}
