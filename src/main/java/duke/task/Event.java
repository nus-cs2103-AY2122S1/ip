package duke.task;

/**
 * Represents the event that has time. .
 *
 * @author QIN GUORUI
 */
public class Event extends Task {
    /**
     * The time to do the event.
     */
    private String at;

    /**
     * Sets up the event task.
     *
     * @param description The content of event task.
     * @param at The time when the task should be done.
     */
    public Event(String description, String at) {
        super(description, at);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
