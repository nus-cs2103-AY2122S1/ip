package duke.tasks;

public class Todo extends Task {

    /**
     * Constructor for the Todo.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
