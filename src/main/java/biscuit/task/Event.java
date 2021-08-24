package biscuit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * biscuit.tasks.Event class: For tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {
    private final LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    public Event(boolean isDone, String description, LocalDateTime date) {
        super(isDone, description, TaskType.EVENT);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + " (at: "
                + date.format(DateTimeFormatter.ofPattern("dd LLL yyyy hh:mm a")) + ")";
    }
}
