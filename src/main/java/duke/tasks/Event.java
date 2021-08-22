package duke.tasks;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for Event task.
     *
     * @param description of the event.
     * @param at when the event is.
     * @param isDone if the task is done or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}