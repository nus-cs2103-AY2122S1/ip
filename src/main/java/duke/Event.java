package duke;

/**
 * Represents a task that has a description and time.
 */
public class Event extends Task {
    protected String deadline;

    /**
     * A constructor to create an Event object.
     *
     * @param description The description of an Event object.
     * @param deadline The deadline of an Event object.
     */
    public Event(String description, String deadline) {
        super(description);
        this.deadline = deadline.equals("") ? "at: " : deadline;
        super.deadline = this.deadline;
    }

    /**
     * Returns the string representation of a Event object.
     *
     * @return The string representation of a Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + deadline + ")";
    }
}
