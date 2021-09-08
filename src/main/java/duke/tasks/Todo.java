package duke.tasks;

/**
 * Represents a to-do, which is a subtype of a Task.
 */
public class Todo extends Task {

    /**
     * A constructor of a To-do.
     * @param description Description of the To-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To-do.
     *
     * @return [T] and the description of the To-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
