package duke.tasks;

/**
 * Represents a Todo task.
 */
public class Todo extends Tasks {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
