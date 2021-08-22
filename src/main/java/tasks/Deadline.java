package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String TAG = "D";
    private LocalDate by;

    /**
     * Constructs a new Deadline object with the specified task description, due date and task status.
     *
     * @param description Description/Tile of the task.
     * @param by Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDueDate() {
        String dueDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return dueDate;
    }

    @Override
    public String toString() {
        return "[" + TAG + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
