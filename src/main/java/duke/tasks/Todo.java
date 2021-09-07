package duke.tasks;

public class Todo extends Task {
    public Todo(String input) {
        super(input.trim());
    }

    public static Todo createTodo(String input) {
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
