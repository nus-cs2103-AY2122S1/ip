package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description Event's description.
     * @param at Event's date.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the Event's details in a String.
     *
     * @return Event's details in a String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns Event's date.
     * @return Event's date.
     */
    public LocalDate getEventDate() {
        return at;
    }

    /**
     * Returns task's details in a format that will be stored in the data file.
     *
     * @return Task's details in a format that will be stored in the data file.
     */
    @Override
    public String toStringData() {
        return "E" + super.toStringData() + "|" + at;
    }

    @Override
    public boolean matches(String query) {
        return super.matches(query)
                || at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toLowerCase().contains(query.toLowerCase())
                || query.equalsIgnoreCase("event");
    }
}
