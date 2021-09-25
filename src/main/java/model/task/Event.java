package model.task;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class for Event with specific time for when the event is occurring.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.01
 */
public class Event extends TimeTask {

    /**
     * Adapted constructor for Event.
     * isDone is set to false.
     *
     * @param description the description of the event.
     * @param at          the time of the event with yyyy-MM-dd format.
     */
    public Event(String description, String at) {
        super(description, at);
    }

    /**
     * Adapted constructor for Event.
     *
     * @param description the description of the event.
     * @param isDone      whether the event is done.
     * @param at          the time of the event with yyyy-MM-dd format.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone, at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getTime(), this.getClass().getName());
    }

    /**
     * Check whether the given object is the same object, is of type Task and have matching description.
     *
     * @param other other object to be checked
     * @return whether that object should equal to this task or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Event)) {
            return false;
        }
        Event otherEvent = (Event) other;
        return otherEvent.getDescription().equals(getDescription()) && otherEvent.getTime().equals(getTime());
    }

    /**
     * Template: "[E][x] description (at: Month DD YYYY)" or "[E][ ] description (at: Month DD YYYY)" for done
     * and not done task respectively.
     *
     * @return the template above for Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + this.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
