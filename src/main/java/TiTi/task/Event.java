package TiTi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task.
 * Contain String description of the event.
 * Contain String description of the date of event.
 * Contain LocalDate object of the date of event (if applicable).
 * Contain boolean value of whether the task has been completed.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    /**
     * Constructor for Event class.
     *
     * @param description description of the event.
     * @param at string description of the time the event is occuring at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        checkDate(at);
    }

    private void checkDate(String at) {
        if (at.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date = LocalDate.parse(at);
            this.at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}