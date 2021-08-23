package duke.task;

/**
 * A task without any deadline.
 *
 * @author limzk126
 * @version Level-7
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

    /**
     * Constructor of Todo class.
     *
     * @param description Description of a task without deadline.
     * @param isDone
     */
    public Todo(String description, Boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    /**
     * Formats task's data into a string for storage in hard disk
     * and returns it.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Todo // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
