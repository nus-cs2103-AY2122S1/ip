package sun.data.task;

/**
 * Class that represents a To Do task.
 *
 * @author Won Ye Ji
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the To Do task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns the string representation of the ToDo task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public String toSave() {
        return toString();
    }
}
