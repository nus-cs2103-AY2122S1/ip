package duke.tasks;

/**
 * A todo task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
