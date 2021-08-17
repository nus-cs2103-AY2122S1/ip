/**
 * Class that encapsulates an ToDo task.
 */
public class ToDo extends Task {

    /**
     * Public constructor to create a ToDo task
     *
     * @param description Description of what to do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of a ToDo task.
     *
     * @return String representation of a ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
