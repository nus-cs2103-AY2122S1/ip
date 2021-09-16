package duke.task;

/**
 * A class that represents to do tasks
 */
public class ToDo extends Task {
    /**
     * Initializes an instance of ToDo class with task type and description.
     * @param type Type of task
     * @param description Task description
     */
    public ToDo(TaskType type, String description) {
        super(type, description);
    }

    /**
     * Initializes an instance of ToDo class with task type, description and
     * whether the task has been completed.
     * @param type Type of task
     * @param description Task description
     * @param isDone A flag to indicate if the task has been completed
     */
    public ToDo(TaskType type, String description, boolean isDone) {
        super(type, description, isDone);
    }

    /**
     * Gets a string representation of the ToDo task.
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.TODO.getAbbr() + "] " + super.toString();
    }
}
