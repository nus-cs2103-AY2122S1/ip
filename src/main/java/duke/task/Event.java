package duke.task;

/**
 * Event Task class used by bot.
 *
 * @author mrmrinal
 */
public class Event extends Task {

    private static final String TASK_TYPE = "E";
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
        String taskDisplay = "[" + TASK_TYPE + "]";
        String timing = " (at: " + this.at + ")";
        return taskDisplay + super.toString()
                + timing;
    }

    @Override
    public String toStorageString() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        String newLine = "\n";
        String taskDisplay = TASK_TYPE + SEPARATOR;
        return taskDisplay + done + SEPARATOR + this.description
                + SEPARATOR + this.at + newLine;
    }
}
