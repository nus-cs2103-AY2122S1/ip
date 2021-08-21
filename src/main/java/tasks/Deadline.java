package tasks;

import tasks.Task;

public class Deadline extends Task {
    private static final String TAG = "D";
    private String by;

    /**
     * Constructs a new Deadline object with the specified task description, due date and task status.
     *
     * @param description Description/Tile of the task.
     * @param by Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDueDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[" + TAG + "]" + super.toString() + " (by: " + by + ")";
    }
}
