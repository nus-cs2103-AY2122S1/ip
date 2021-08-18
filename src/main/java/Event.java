public class Event extends Task {

    /** Time/place of an event */
    protected String at;

    /**
     * Constructor for Event. Takes in a String description and at.
     * Initialises description and time of event.
     *
     * @param description The name of the event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}