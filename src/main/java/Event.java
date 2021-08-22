/**
 * A subclass of Task with scheduled date.
 */
public class Event extends Task {
    /**
     * Scheduled date of the task.
     */
    private String at;

    /**
     * Instantiates an Event
     *
     * @param description description of the event
     * @param at scheduled date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the string representation of this Event object.
     *
     * @return String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + "|" + this.at;
    }
}
