/**
 * Encapsulates an event object.
 * Event objects are task objects that start and end at specific times.
 *
 * @author Dickson
 */
public class Event extends Task {
    /**
     * Constructor for Event object.
     *
     * @param description
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Gets string representation of event task.
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
