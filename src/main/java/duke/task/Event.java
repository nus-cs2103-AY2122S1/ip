package duke.task;

/**
 * Represents the Event task in the Duke program.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event task with the given description.
     *
     * @param description Description of the Event task.
     * @param at          Datetime of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of this Event task to be saved in storage.
     *
     * @return the string representation of this Event task to be saved in storage.
     */
    @Override
    public String toStringData() {
        return String.format("E | %s | %s", super.toStringData(), at);
    }

    /**
     * Returns a description of this Event task.
     *
     * @return a description of this Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
