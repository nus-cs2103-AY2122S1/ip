/**
 * A task that is scheduled for a specific time.
 */
public class Event extends Task{
    protected String time;

    /**
     * Constructor for the Event class.
     * @param description Description of the task at hand.
     * @param time Time the event is scheduled to take place at.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Provides a String representation of the Event.
     * @return A String reprsentation of the Event.
     */
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description + "(at: " + this.time + ")";
    }
}
