package duke.task;

/**
 * Todo Task class used by bot.
 *
 * @author mrmrinal
 */
public class ToDo extends Task {

    private static final String TASK_TYPE = "T";

    /**
     * Creates new Todo task
     *
     * @param description Description of Event
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates new Todo task
     *
     * @param description Description of Event
     * @param done Status of Event
     */
    public ToDo(String description, int done) {
        super(description, done);
    }

    @Override
    public String toString() {
        String taskDisplay = "[" + TASK_TYPE + "]";
        return taskDisplay + super.toString();
    }
}
