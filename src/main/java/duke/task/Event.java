package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event task.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructor for this event.
     *
     * @param description Details of event.
     * @param at Date of event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns string of event in the format to be saved.
     *
     * @return String representation of event to be saved.
     */
    @Override
    public String saveTaskFormat(){
        return "E" + super.saveTaskFormat() + String.format("|%s", at);
    }

    /**
     * Returns string of event in list.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        String formattedAt = at.format(formatter);
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}
