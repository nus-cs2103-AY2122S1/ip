package duke.task;

/**
 * Represents todos which are tasks that are to be done.
 *
 * @author botr99
 */
public class Todo extends Task {
    /**
     * Constructs a todo with the corresponding description,
     * that is by default not done.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Constructs a todo with the corresponding description,
     * and whether it is done or not done.
     *
     * @param description Description of the todo.
     * @param isDone True if todo is done; false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string indicating whether a todo is done,
     * followed by the description of the todo.
     *
     * @return The string representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string indicating whether a todo is done,
     * followed by the description of the todo.
     *
     * @return The string representation of a todo to be stored in storage
     *         under the user's hard disk.
     */
    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

}
