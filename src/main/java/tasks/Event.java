package tasks;

import tasks.Task;

public class Event extends Task {
    private static final String TAG = "E";
    private String at;

    /**
     * Constructs a new Event object with the specified task description, due date and task status.
     *
     * @param description Description/Tile of the task.
     * @param at Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDueDate() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[" + TAG + "]" + super.toString() + " (at: " + at + ")";
    }
}
