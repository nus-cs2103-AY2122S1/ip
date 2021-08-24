package duke.task;

import duke.task.Task;

/**
 * Represents the event that has time. .
 *
 * @author QIN GUORUI
 */
public class Event extends Task {
    /**
     * The time to do the event.
     */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = dateAndTime(at);
    }

    /**
     * Returns whether the two times are equal.
     *
     * @param time The time's string representation.
     * @return A boolean.
     */
    @Override
    public boolean compareTime(String time) {
        return at.equals(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
