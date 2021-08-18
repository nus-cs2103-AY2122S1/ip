/**
 * A task of type Event
 */
public class Event extends Task {

    protected String at;

    /**
     * constructor of the class
     * @param description description of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String representation of event
     * @return String representation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}