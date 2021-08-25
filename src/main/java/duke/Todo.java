package duke;

/**
 * Basic task that can be initialized.
 */
public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description);
    }
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }
}
