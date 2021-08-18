/**
 * the task in the list
 */
public class Task {
    /**
     * description of the task
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
     * Gets the task status icon
     * @return "X" if the test is done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public String doneTask() {
        this.isDone = true;
        String str = "Nice! I've marked this task as done: \n" + this.toString();
        return str;
    }

    /**
     * The task string representation
     * @return The description of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}