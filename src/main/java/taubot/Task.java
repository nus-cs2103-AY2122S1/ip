package taubot;

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
        DEADLINE,
        NULL
    }

    /**
     * The <code>TaskType</code> of the task.
     */
    private TaskType type;

    /**
     * The description of the task.
     */
    private final String taskDescription;

    /**
     * Represents the completion status of a task.
     */
    private boolean isDone;

    /**
     * Constructor for Task object, takes in taskDescription string.`
     *
     * @param taskDescription Description of the task.
     */
    protected Task(TaskType type, String taskDescription) {
        this.taskDescription = taskDescription;
        this.type = type;
        isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return A string representing task status.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns string representation of Task object.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskDescription;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public TaskType getType() {
        return type;
    }
}
