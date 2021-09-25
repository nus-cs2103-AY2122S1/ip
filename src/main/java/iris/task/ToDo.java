package iris.task;

/**
 * ToDo is a Task with a deadline.
 * Contains the description of task.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class ToDo extends Task {

    /**
     * Constructor of an ToDo.
     *
     * @param description Description of task to be done.
     * @param isDone      Boolean representation of completion of task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
        assert !description.isBlank() : "Description of todo cannot be blank.";
    }

    /**
     * Returns a string representation of the status of task
     * containing the type of task as denoted by T,
     * completion status,
     * the task to be completed
     * @return String representation of status of task.
     */
    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    /**
     * Returns string representation of ToDo task.
     * Used when storing data in local directory.
     *
     * @return String representation of a Todo.
     */
    @Override
    public String toString() {
        return "T" + " | " + super.toString();
    }
}
