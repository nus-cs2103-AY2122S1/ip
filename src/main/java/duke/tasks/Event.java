package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected String at;
    private LocalDate date;

    /**
     * Constructor for Event task.
     *
     * @param description of the event.
     * @param at          when the event is.
     * @param isDone      if the task is done or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Constructor for Event task.
     *
     * @param description of the event.
     * @param at          when the event is.
     * @param isDone      if the task is done or not.
     * @param date        date of event.
     */
    public Event(String description, String at, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.at = at;
        this.date = date;
    }

    public String getAt() {
        return at;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + at + ")";
        }
    }
}
