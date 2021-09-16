package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class to represent an event.
 */
public class Event extends Task {

    /** representation of the event date */
    protected LocalDateTime at;

    /**
     * Constructor of Event class.
     *
     * @param name name of task
     * @param at date of the event
     */
    public Event(String name, LocalDateTime at) {
        super(name);
        assert(at != null);
        this.at = at;
    }

    /**
     * Constructor of Event class.
     *
     * @param name name of task
     * @param isDone whether the task has been completed
     * @param at date of the event
     */
    public Event(String name, boolean isDone, LocalDateTime at) {
        super(name, isDone);
        assert(at != null);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event instance.
     *
     * @return the string representation of the Event instance
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + this.at.format(formatter) + ")";
    }

    /**
     * Returns the string to be recorded in the data file of the Event instance.
     *
     * @return the string to be recorded of the Event instance
     */
    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        String record = String.format("E | %d | %s | %s", done, this.name, this.at.format(formatter));
        return record;
    }

    /**
     * Determines if two instances of Event are equal.
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two Event instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event ev = (Event) obj;
            return this.name.equals(ev.name) && this.at.equals(ev.at) && this.isDone == ev.isDone;
        }
        return false;
    }
}
