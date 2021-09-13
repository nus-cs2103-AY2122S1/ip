package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event's date and time
     *
     * @return The event date and time.
     */
    public String getAt() {
        assert at != null : "at variable should not be null";
        return at;
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        assert at != null : "at variable should not be null";
        return "[E]" + super.toString() + " (at: " + at + ")";


    }
}
