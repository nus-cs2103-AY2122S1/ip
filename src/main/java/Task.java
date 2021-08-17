/**
 * This class encapsulates a task.
 */
public class Task {
    /**
     * The description of the task.
     */
    private String taskDescription;

    /**
     * Represents the completion status of a task.
     */
    private boolean isDone;

    /**
     * Constructor for Task object, takes in taskDescription string.`
     * @param taskDescription Description of the task.
//     */
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
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskDescription;
    }
}
