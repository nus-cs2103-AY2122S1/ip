/**
 * Task class to represent the tasks in the list.
 * It supports (i) getting status icon
 * and (ii) marking a task as done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class,
     * initially the task is not done.
     * @param description the name of the task
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the status icon of the Task.
     * @return the status icon based on whether the task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the current task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
