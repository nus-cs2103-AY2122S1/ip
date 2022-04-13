package duke.commands;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Encapsulates a task that is an event.
 * Similar to a task, an event has a description,
 * but also contains the date/time for the event.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Event extends Task {
    /** Event time of the task */
    protected LocalDateTime at;

    /**
     * Constructor for an event.
     *
     * @param description The description of the event.
     * @param at The date/time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description, "E");
        this.at = at;
    }

    /**
     * Returns event time of Event task.
     * Represented in a LocalDateTime format.
     *
     * @return event time of task.
     */
    public LocalDateTime getAt() {
        return this.at;
    }

    /**
     * Returns string representation of Event task.
     * Method override from toString() of parent class Task.
     *
     * @return string representation of Event.
     */
    @Override
    public String toString() {
        String month = this.at.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int date = this.at.getDayOfMonth();
        int year = this.at.getYear();
        int hour = this.at.getHour();
        int minute = this.at.getMinute();

        String toPrint = String.format("[E]%s (at: %s %d %d %s:%s)",
                super.toString(), month, date, year, hour, minute);
        return toPrint;
    }

    /**
     * Marks task as done, and returns string representation of event task being marked done.
     * Method override from markDone() of parent class Task.
     *
     * @return string representation of event task being marked done.
     */
    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [E][X] %s", this.description);
    }
}
