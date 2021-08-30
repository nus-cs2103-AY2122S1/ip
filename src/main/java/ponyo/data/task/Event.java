package ponyo.data.task;

/**
 * An Event task object that has a description and an at-date.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for a new event instance.
     *
     * @param description a string description of the event
     * @param at a string at-date for the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStringInFile() {
        return "E - " + super.toStringInFile() + " - " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
