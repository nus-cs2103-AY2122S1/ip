package jarvis.task;

/**
 * Encapsulates the todo task which contains a description.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description The todo task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the string representation of an todo task.
     *
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Gets the string representation of a todo task that is to be saved to storage file.
     *
     * @return String representation that is to be saved.
     */
    @Override
    public String toStorageFormatString() {
        return String.format("%s;;;%s", "T", super.toStorageFormatString());
    }
}
