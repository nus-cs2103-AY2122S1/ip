package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that represents a Todo that will be saved by Dub.
 */
public class Event extends Task {

    /** The time of the event. */
    private final LocalDate time;

    /**
     * Constructor of the Event class.
     *
     * @param description Description of the Event.
     * @param time Time when the Event happens.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a copy of the current task.
     *
     * @return A copy of the task.
     */
    public Event copyTask() {
        Event task = new Event(description, time);
        task.isDone = isDone;
        return task;
    }

    /**
     * Returns true if two objects are equal.
     *
     * @param obj Object that will be compared.
     * @return True if the object are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event temp = (Event) obj;
            return temp.toString().equals(this.toString());
        }

        return false;
    }

    /**
     * Returns string implementation of the Event object.
     *
     * @return String implementation of the Event.
     */
    @Override
    public String toString() {
        String timeString = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.isDone) {
            return "[E][X] " + this.description + " (at: "
                    + timeString + ")";
        }
        return "[E][ ] " + this.description + " (at: "
                + timeString + ")";
    }
}
