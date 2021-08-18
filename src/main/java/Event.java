public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event
     * @param description the description of the event task
     * @param at the timing of the event
     */
    public Event(String description, String at) throws EmptyDescriptionException{
        super(description);
        this.at = at;
    }

    /**
     * Returns string representation of Event
     * @return string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
