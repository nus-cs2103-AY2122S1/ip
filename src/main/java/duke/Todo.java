package duke;

public class Todo extends Task {
    /**
     * Extension of generic task that indicates that the task has no extra information with a [T] prefix.
     * @param description description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[T]%s", stem);
    }
}
