package eightbit.task;

/**
 * Represents a todo item.
 */
public class ToDo extends Task {

    /**
     * Constructs a todo.
     *
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a todo. Marks the todo as done or not.
     *
     * @param description Description of the todo.
     * @param isDone Whether the todo is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return String representation of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
