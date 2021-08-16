public class Event extends Task {
    private String at;

    /**
     * Constructor of the Event class
     *
     * @param description description of this event
     * @param at the time period of this event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of this event
     *
     * @return string representation of this event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.at + ")";
    }
}