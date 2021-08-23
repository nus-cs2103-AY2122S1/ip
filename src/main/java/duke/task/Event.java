package duke.task;

/**
 * Encapsulates event date.
 *
 * @author limzk126
 * @version Level-6
 */
public class Event extends Task {
    protected DateTime at;

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param at Date/time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = new DateTime(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")\n";
    }
}
