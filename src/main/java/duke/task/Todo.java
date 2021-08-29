package duke.task;

/**
 * Represents the To-do tasks.
 *
 * @author QIN GUORUI
 */
public class Todo extends Task {
    /**
     * Sets up a todo task.
     *
     * @param description The content of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
