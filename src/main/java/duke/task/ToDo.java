package duke.task;

/**
 * Todo Task class used by bot.
 *
 * @author mrmrinal
 */
public class ToDo extends Task {

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
        return "[T]" + super.toString();
    }
}
