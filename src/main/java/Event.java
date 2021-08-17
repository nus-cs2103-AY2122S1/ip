/**
 * A Task of type event
 */
public class Event extends Task {

    /**
     * When is the event?
     */
    String at;

    /**
     * The Constructor
     * @param description The description of the task
     * @param at The time of the event, start and end.
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
