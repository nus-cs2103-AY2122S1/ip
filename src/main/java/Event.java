/**
 * The Event class extends Task class and encapsulate an event task
 * that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at +")";
    }
}
