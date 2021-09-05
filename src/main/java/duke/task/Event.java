package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class which is a subclass of Task.
 */
public class Event extends Task {

    // Time which event happen at
    protected String at;

    /**
     * Constructor for the event task.
     *
     * @param description the description of the event task.
     * @param at the date where the event happens.
     */
    public Event(String description, String at) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(at);
            this.at = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return string representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    @Override
    public String toDataFormat() {
        return String.format("E | %s | %s | %s | %s", isDone ? "1" : "0",
                description, getPlacesRepresentation(), at);
    }
}
