package duke.tasks;

/**
 * Represents a task a user has to do.
 */
public class Todo extends Task {

    /**
     * Default constructor for a Todo task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T | " + super.getData();
    }
}
