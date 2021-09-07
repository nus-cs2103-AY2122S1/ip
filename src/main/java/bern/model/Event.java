package bern.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class to represents a Task of type "Event".
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description The description of the Task.
     * @param at When the task is.
     */
    public Event(String description, String at) {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    /**
     * A method to return the String representation of the class.
     *
     * @return The String representation of the class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd YYYY")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        } else {
            Event compared = (Event) obj;
            if (compared.description.equals(this.description) && compared.at.equals(this.at)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
