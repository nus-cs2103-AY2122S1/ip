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
    private String formatAt;

    /**
     * Sets up the event task.
     *
     * @param description The content of event task.
     * @param formatAt The time when the task should be done(in format).
     */
    public Event(String description, String formatAt) {
        super(description, formatAt);
        this.formatAt = formatAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatAt + ")";
    }
}
