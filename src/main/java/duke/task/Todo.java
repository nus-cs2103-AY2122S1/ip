package duke.task;

import duke.exception.IncompleteTaskDescriptionException;

/**
 * Todo is a specific type of task that contains the description of the task.
 */
public class Todo extends Task {
    private static final char TASK_LETTER = 'T';

    /**
     * Constructs a todo task.
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
     * Converts todo to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    @Override
    public String stringToStore() {
        return String.format("%c | %s | %s\n", Todo.TASK_LETTER, this.getStatusIcon(), this.description);
    }

    /**
     * Creates a new todo object.
     *
     * @param description The description of the task.
     * @param isDone Whether the todo is done.
     * @return The todo object created.
     * @throws IncompleteTaskDescriptionException If the description is empty or in incorrect format.
     */
    public static Todo create(String description, boolean isDone) throws IncompleteTaskDescriptionException {
        if (!description.equals("")) {
            return new Todo(description, isDone);
        } else {
            throw new IncompleteTaskDescriptionException("todo");
        }
    }
}
