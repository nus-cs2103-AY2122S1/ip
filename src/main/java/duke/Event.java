package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an Event task.
 *
 * @author Kleon Ang
 */
public class Event extends Task {
    private static final String taskBadge = "[E]";
    private final LocalDateTime at;

    /**
     * Constructor for an Event task.
     *
     * @param description A string describing the Event task.
     * @param at A LocalDateTime indicating when the event is held at.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a data string of the Event task for saving.
     *
     * @return A data string including the Event's icon, description and time.
     */
    @Override
    public String getDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
        return "E | " + super.getDataString() + " | " + this.at.format(formatter);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string including the Event's icon, description and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return taskBadge + super.toString() + "\n  (at: " + this.at.format(formatter) + ")";
    }
}
