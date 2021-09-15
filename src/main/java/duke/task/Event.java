package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a specific type of Task like Events.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime at;

    /**
     * Constructs a Event task.
     *
     * @param description The description of the task.
     * @param at The date and time of the event.
     */
    public Event(String description, String priority, String at) {
        super(description, priority);
        this.at = LocalDateTime.parse(at.trim(), DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    /**
     * Converts an Event into the format for storing.
     *
     * @return A String specific to an Event that follows the storing conventions for our data file.
     */
    @Override
    public String convertToFileFormat() {
        String temp = this.at.toString().replace("T", " ").replace(":", "");
        return String.format("E%s | %s", super.convertToFileFormat(), temp);
    }
}
