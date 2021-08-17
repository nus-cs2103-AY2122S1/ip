/**
 * This class encapsulates a task.
 */
public class Task {

    /**
     * Enumeration for the different task types.
     */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * The description of the task.
     */
    private String taskDescription;

    /**
     * Represents the completion status of a task.
     */
    private boolean isDone;

    public TaskType type;

    /**
     * Constructor for Task object, takes in taskDescription string.`
     * @param taskDescription Description of the task.
    */
    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representing the completion status of the task.
     * @return A string representing task status.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns string representation of Task object.
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskDescription;
    }
}
