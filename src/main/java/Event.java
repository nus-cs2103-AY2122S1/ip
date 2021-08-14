/**
 * A wrapper for an Event which is a Task that start at a specific date/time.
 *
 * @author Wong Yun Rui Chris
 */
public class Event extends Task {
    protected String at;

    /**
     * A public constructor to initialise an Event Task.
     *
     * @param description The String description/name of the task
     * @param at The String describing when the Event takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
