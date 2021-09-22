package duke;

/**
 * Basic task that can be initialized.
 */
public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description);
    }

    public Todo clone() {
        try {
            Todo clone = new Todo(description);
            if (isDone) {
                clone.markAsDone();
            }
            return clone;
        } catch (DukeException e) {
            return null; // This Exception can never happen.
        }
    }

    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }
}
