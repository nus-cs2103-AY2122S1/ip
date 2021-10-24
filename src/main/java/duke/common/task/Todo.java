package duke.common.task;

/**
 * Extension of generic task that indicates that the task has no extra information with a [T] prefix.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[T]%s", stem);
    }
}
