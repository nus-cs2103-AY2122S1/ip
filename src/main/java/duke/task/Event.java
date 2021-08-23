package duke.task;

/**
 * Encapsulates event date.
 *
 * @author limzk126
 * @version Level-7
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

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param at Data/time of event.
     * @param isDone Completion status of event.
     */
    public Event(String description, String at, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.at = at;
    }

    /**
     * Formats task's data into a string for storage in hard disk
     * and returns it.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Event // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")\n";
    }
}
