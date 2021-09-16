package titi.task;

/**
 * Represents the ToDo task.
 * Contains String description of the task.
 * Contains boolean value of whether the task has been completed.
 */
public class ToDo extends Task {

    /**
     * Initialises a ToDO instance.
     *
     * @param description description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
