/**
 * Represents an Event, which is a Task with a starting time and an ending time.
 */
public class Event extends Task {
    /** Time range of the event */
    protected String at;

    /**
     * Constructor of the class `Event`.
     *
     * @param description Description of the task.
     * @param at Time slot of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts a task with time range to string.
     *
     * @return The string representation of a task with time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Converts the task to a string with the format of the file in hard disk.
     *
     * @return String representation of the task in the file's format.
     */
    @Override
    public String toFileFormatString() {
        return String.format("E / %s / %s / %s\n", this.isDone ? "1" : "0", this.description, this.at);
    }
}
