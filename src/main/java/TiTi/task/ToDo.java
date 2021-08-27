package TiTi.task;

/**
 * Represents the ToDo task.
 * Contain String description of the task.
 * Contain boolean value of whether the task has been completed.
 */
public class ToDo extends Task {

    /**
     * Constructor for TODO class.
     *
     * @param description description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}