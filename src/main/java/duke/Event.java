package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Encapsulates an event task with a start date.
 */
public class Event extends Task {
    private LocalDateTime startDate;

    /**
     * Constructs an Event task object.
     *
     * @param description Task description.
     * @param isCompleted Completion status of task.
     * @param startDate Start date of task.
     */
    public Event(String description, Boolean isCompleted, LocalDateTime startDate, ArrayList<String> tags) {
        super(description, isCompleted, tags);
        this.startDate = startDate;
    }

    @Override
    public String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString()
                + " (at: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + ")" + super.getTags();
    }

    @Override
    public String toFileString() {
        return super.toString() + " (at: " + startDate + ")" + super.getTags();
    }
}
