package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for Event class
     * initializing a Event instance with the given task description and time.
     *
     * @param description Task description
     * @param at Time of event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event changeTime(LocalDate newTiming) {
        return new Event(this.description, newTiming);
    }

    @Override
    public String formatForFile() {
        return "E " + super.formatForFile() + " | " + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}
