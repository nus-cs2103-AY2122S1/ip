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
     * @param description description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
