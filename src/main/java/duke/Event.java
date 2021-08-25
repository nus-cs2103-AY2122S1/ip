package duke;

/**
 * This class encapsulates an event task in the Duke Application.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for a new Event task.
     *
     * @param description The task description.
     * @param at The time of this task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for a new Event task.
     *
     * @param description The task description.
     * @param at The time of this task.
     * @param isDone Whether this task is marked as done.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E,%s,%s", super.toSaveFormat(), this.at);
    }
}
