package main.java;

public class Event extends Task {

    protected String at;

    /**
     * Constructor for new Event object.
     * @param description description of the event.
     * @param at date in which the event is to be held at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Retrieves this event's at.
     * @return at.
     */
    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
