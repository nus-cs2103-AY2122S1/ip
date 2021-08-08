/**
 * The Task
 */
public class Task {
    /**
     * The task description
     */
    protected String description;
    /**
     * The task status. True if it is done, else false
     */
    protected boolean isDone;

    /**
     * Create a new Task object (Constructor)
     * @param description The new task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets the task status icon
     * @return "X" if the test is done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * The task string representation
     * @return The description of the task
     */
    public String toString() {
        return this.description;
    }
}