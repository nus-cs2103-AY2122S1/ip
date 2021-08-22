package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public static final String TAG = "E";
    private LocalDate at;

    /**
     * Constructs a new Event object with the specified task description, due date and task status.
     *
     * @param description Description/Tile of the task.
     * @param at Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTag() {
        return Event.TAG;
    }

    @Override
    public String getDueDate() {
        return this.at.toString().trim();
    }

    @Override
    public String toString() {
        return "[" + Event.TAG + "]" + super.toString()
                + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
