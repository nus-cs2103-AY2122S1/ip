/**
 * Represents a task that starts at a specific time and ends at a specific time.
 *
 * @author felix-ong
 */
public class Event extends Task {
    /** Date, start and end time of the event */
    protected String dateTime;

    /**
     * Constructor of a Deadline Task.
     *
     * @param description
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of a Deadline Task.
     *
     * @return The string representation of a Deadline Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }
}
