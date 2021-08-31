package duke;

import java.time.LocalDateTime;

/**
 * Encapsulates an event task with a start date.
 */
public class Event extends Task {
    private LocalDateTime startDate;

    public Event(String description, Boolean completionStatus, LocalDateTime startDate) {
        super(description, completionStatus);
        this.startDate = startDate;
    }

    @Override
    public String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + startDate + ")";
    }
}
