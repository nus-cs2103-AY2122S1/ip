package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    /**
     * Event task constructor
     *
     * @param description
     * @param at
     * @param done
     */
    public Event(String description, LocalDate at, boolean done) {
        super(description, done);
        this.at = at;
    }

    /**
     * Parse date into MMM dd yyyy format
     *
     * @return date in MMM dd yyyy format
     */
    public String parseDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Overrides toString method
     *
     * @return description of task and date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + parseDate() + ")";
    }
}
