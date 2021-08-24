package duke.task;

/**
 * Represents a task which encapsulates a description
 * and a boolean flag indicating if the task is complete.
 *
 * @author Joshua Yong
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     *
     * @param description The given task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Outputs this task as a String.
     *
     * @return String representation of the task.
     */
    public String toString() {
        String statusIcon = this.isDone ? "[X]" : "[ ]"; // mark done task with X
        return statusIcon + " " + description;
    }

    /**
     * Sets the isDone boolean flag of the task to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
