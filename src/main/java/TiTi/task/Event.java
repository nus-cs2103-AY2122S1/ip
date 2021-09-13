package titi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task.
 * Contains String description of the event.
 * Contains String description of the date of event.
 * Contains LocalDate object of the date of event (if applicable).
 * Contains boolean value of whether the task has been completed.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    /**
     * Initialises an Event instance.
     *
     * @param description description of the event
     * @param at string description of the time the event is occurring at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        checkDate(at);
    }

    /**
     * Checks if the string input can be converted to a LocalDate object.
     */
    private void checkDate(String at) {
        if (at.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date = LocalDate.parse(at);
            this.at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
