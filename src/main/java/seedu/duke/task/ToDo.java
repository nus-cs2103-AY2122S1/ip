package seedu.duke.task;

/**
 * Class that encapsulates an duke.task.ToDo duke.task.
 */
public class ToDo extends Task {

    /**
     * Public constructor to create a duke.task.ToDo duke.task
     *
     * @param description Description of what to do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of a duke.task.ToDo duke.task.
     *
     * @return String representation of a duke.task.ToDo duke.task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
