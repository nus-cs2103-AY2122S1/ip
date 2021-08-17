package tasks;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task{
    protected String timing;

    /**
     * Creates an Event with a specified description and timing.
     * @param description The description of this Event.
     * @param timing The timing of this Event.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Returns this task's status and description.
     * @return A string showing what the task is, its description and its timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing + ")";
    }
}
