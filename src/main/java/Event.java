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
     * @param isDone The Boolean of if the task is done
     */
    public Event(String description, String at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toData() {
        return "[E] | " + super.toData() + " | " + this.at;
    }
}
