package duke;

/**
 * Class representing an Event.
 */
public class Event extends Task {

    protected String at;
    /**
     * Constructor of a Event, defaults to not completed.
     *
     * @param description Description of the Event.
     * @param at Date of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor of a Event.
     *
     * @param description Description of the Event.
     * @param isCompleted If the event is completed.
     * @param at Date of the event.
     */
    public Event(String description, Boolean isCompleted, String at) {
        super(description, isCompleted);
        this.at = at;
    }

    /**
     * Returns the Date of the Event.
     *
     * @return Date of the Event.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns String representation of the Event in save format.
     *
     * @return String representation of the Event in save format.
     */
    @Override
    public String save() {
        return "E|" + (this.getStatus() ? "1" : "0") + "|" + this.getDescription() + "|" + this.getAt();
    }
}