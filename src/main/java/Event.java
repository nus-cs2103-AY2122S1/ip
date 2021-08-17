public class Event extends Task {
    /**
     * String representation of date of Event.
     */
    protected String at;

    /**
     * Constructor for Event object.
     * @param description Description of Event.
     * @param at Date of Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns string representation of Event object.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
