/**
 * Represents a Event Task which inherits from Task and contains the dateTime for the event.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Event extends Task {
    private String dateTime;
    
    /**
     * Constructor to initialise a new Event.
     *
     * @param description The description of the task.
     * @param dateTime The date and time of the event.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Overridden toString method.
     *
     * @return The String representation of the task, prefixed with a status icon and events identifier.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.dateTime);
    }
}
