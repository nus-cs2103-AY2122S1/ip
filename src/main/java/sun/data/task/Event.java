package sun.data.task;

/**
 * Class that represents an Event task.
 *
 * @author Won Ye Ji
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the task.
     * @param at Date of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate(at) + ")";
    }

    /**
     * Returns the string representation of the Event task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public String toSave() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
