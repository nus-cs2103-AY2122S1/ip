package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String TAG = "D";
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
        return Deadline.TAG;
    }

    @Override
    public String getDueDate() {
        return this.by.toString().trim();
    }

    @Override
    public String toString() {
        return "[" + Deadline.TAG + "]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
