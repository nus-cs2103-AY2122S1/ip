/**
 * Class for todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task.
     *
     * @param description String describing the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Get string representation of a todo task.
     *
     * @return String describing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
