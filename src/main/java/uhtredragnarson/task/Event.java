package uhtredragnarson.task;

/**
 * The Event class extends the Task class and represents an event that begin and
 * ends at specific timings.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for this class.
     *
     * @param title Description of the event.
     * @param at    Timing of the event.
     */
    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.title + " (at: " + at + ")";
    }
}
