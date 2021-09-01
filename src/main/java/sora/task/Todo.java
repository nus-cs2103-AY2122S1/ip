package sora.task;

/**
 * Represents a todo.
 * A todo is the same as a task.
 *
 * @author Zhang Shi Chen
 */
public class Todo extends Task {
    /**
     * Constructor for todo.
     *
     * @param description Description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats todo in the form of: [T][ ] description
     *
     * @return A string representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
