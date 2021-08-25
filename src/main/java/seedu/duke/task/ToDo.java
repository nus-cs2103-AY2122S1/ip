package seedu.duke.task;

/**
 * Represents a ToDo task. A <code>ToDo</code> describes
 * the task to be done.
 */
public class ToDo extends Task {

    /**
     * Public constructor to create a <code>ToDo</code>
     * object.
     *
     * @param description Description of what to do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of a <code>ToDo</code>.
     *
     * @return String representation of a <code>ToDo</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
