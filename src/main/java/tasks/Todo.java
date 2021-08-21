package tasks;

public class Todo extends Task {
    private static final String TAG = "T";

    /**
     * Constructs a new Todo object with the specified task description and task status.
     *
     * @param description Description/Tile of the task.
     * @param isDone Completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return "[" + TAG + "]" + super.toString();
    }
}
