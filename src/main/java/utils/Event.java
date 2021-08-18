package utils;

/**
 * The Deadline class encapsulates a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
