package duke.task;

/**
 * This class encapsulates an event task in the Duke Application.
 */
public class Event extends Task {
    private String eventTime;

    /**
     * Constructor for a new Event task.
     *
     * @param description The task description.
     * @param eventTime   The time of this task.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Constructor for a new Event task.
     *
     * @param description The task description.
     * @param at          The time of this task.
     * @param isDone      Whether this task is marked as done.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.eventTime = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventTime);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E,%s,%s", super.toSaveFormat(), this.eventTime);
    }
}
