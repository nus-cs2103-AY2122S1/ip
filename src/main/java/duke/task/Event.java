package duke.task;

/**
 * Event Task class used by bot.
 *
 * @author mrmrinal
 */
public class Event extends Task {

    private final String at;

    /**
     * Creates new Event task.
     *
     * @param description Description of Event
     * @param at Time of Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates new Event task.
     *
     * @param description Description of Event
     * @param at Time of Event
     * @param done Status of Event
     */
    public Event(String description, String at, int done) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.at + ")";
    }

    @Override
    public String toStorageString() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "E | " + done + " | " + this.description
                + " | " + this.at + "\n";
    }
}
