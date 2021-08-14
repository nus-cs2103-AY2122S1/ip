/**
 * A wrapper for a Deadline which is a Task that needs to be done before a date/time.
 *
 * @author Wong Yun Rui Chris
 */
public class Deadline extends Task {
    protected String by;

    /**
     * A public constructor to initialise a Deadline Task.
     *
     * @param description The String description/name of the task
     * @param by The String describing when the Deadline need to be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
