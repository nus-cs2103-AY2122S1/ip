/**
 * A subclass of Task with scheduled date.
 */
public class Event extends Task {
    /**
     * Scheduled date of the task.
     */
    private String at;

    /**
     * Constructor to instantiate an Event
     *
     * @param description description of the event
     * @param at scheduled date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * A method to return the string representation of this Event object.
     *
     * @return String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
