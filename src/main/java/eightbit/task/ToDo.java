package eightbit.task;

import java.util.List;

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
     * Constructs a todo.
     * Marks the todo as done or not. Adds a list of tags to the task.
     *
     * @param description Description of the todo.
     * @param isDone      Whether the todo is done.
     * @param tags        List of tags.
     */
    public ToDo(String description, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Returns the string representation of a ToDo.
     *
     * @return String representation of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
