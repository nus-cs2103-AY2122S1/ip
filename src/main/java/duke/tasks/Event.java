package duke.tasks;

/**
 * Reflects a particular Event task
 */
public class Event extends Task {

    /** Indicates the date that the event is held  */
    protected String at;

    /**
     * Constructor to create an event task containing a description, and the location/date of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Internal constructor called to indicate that the Event task is completed.
     */
    private Event(String description, String at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Outputs the date of the Event.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Indicates that the Event task is completed.
     */
    public Event markAsDone() {
        return new Event(this.description, this.at, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
