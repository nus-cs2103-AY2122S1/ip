package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a task that takes in description and deadline.
 */
public class Event extends Task {
    protected LocalDate deadline;
    protected String logo = "[E]";

    public Event(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns logo corresponding to type.
     *
     * @return the logo [E]
     */
    public String logo() {
        return logo;
    }

    private String dateFormat() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String saveWithFormat () {
        return logo() + isDone + "_" + super.description + "_" + deadline;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.description + " (at: " + dateFormat() + " " + ")";
    }
}