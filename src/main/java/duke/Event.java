package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Event</code> type of task.
 */
public class Event extends Task {
    private final LocalDate date;

    /**
     * Constructs an instance of <code>Event</code>
     * @param body Body message
     * @param date Date of task
     */
    Event(String body, LocalDate date) {
        super(body, false);
        this.date = date;
    }

    /**
     * Constructs an instance of <code>Event</code>
     * @param body Body message
     * @param done Status of task
     * @param date Date of task
     */
    Event(String body, boolean done, LocalDate date) {
        super(body, done);
        this.date = date;
    }

    /**
     * Returns date of <code>Event</code>
     * @return Date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns <code>Event</code> with done set to true
     * @return Done <code>Event</code>
     */
    @Override
    Task setDone() {
        return new Event(this.getBody(), true, this.date);
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[E] [X]"
                    + this.getBody()
                    + " (at: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ")";
        }
        return "[E] [ ]"
                + this.getBody()
                + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
