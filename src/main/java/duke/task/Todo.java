package duke.task;

/**
 * A task without any deadline.
 *
 * @author limzk126
 * @version Level-5
 */
public class Todo extends Task {
    /**
     * Constructor of Todo class.
     *
     * @param description Description of a task without deadline.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
