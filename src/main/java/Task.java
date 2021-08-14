/**
 * This Task class implements the characteristics of a task.
 *
 * @author Yeo Jun Wei
 * @version Level-4
 */
public class Task {
    /** The description of the task */
    protected String description;

    /** To indicate if the task has been done */
    protected boolean isDone;

    /**
     * Constructor for a Task instance that takes in a description.
     * The task is initially not done.
     *
     * @param description The given task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon, indicating if the current task has been done.
     *
     * @return An icon representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of a Task instance.
     *
     * @return A string representing a Task instance.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
