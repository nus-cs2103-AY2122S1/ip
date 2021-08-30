package duke.task;

/**
 * Todo is a specific type of task that contains the description of the task.
 */
public class Todo extends Task {
    private static final char TASK_LETTER = 'T';

    /**
     * Constructs a todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task. It is used to instantiate a todo that is already marked as done.
     *
     * @param description The description of the task.
     * @param isDone Whether todo is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string representation of todo.
     *
     * @return A string representing todo.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s", Todo.TASK_LETTER, super.toString());
    }

    /**
     * Convert todo to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    @Override
    public String stringToStore() {
        return Todo.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + "\n";
    }
}
