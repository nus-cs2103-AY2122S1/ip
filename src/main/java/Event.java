public class Event extends Task {

    protected String at;

    /**
     * Constructor for an Event.
     * @param description The description of th event.
     * @param at The location of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]"+ super.toString() + " (at: " + at + ")";
    }

}
