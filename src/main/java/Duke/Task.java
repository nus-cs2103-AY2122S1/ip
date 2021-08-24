package Duke;

/**
 * A Parent class for the different types of input to the Task List.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A public constructor to create a task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task's status.
     * @return the string representation of the task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Returns the string representation of the task
     * to be saved into the file.
     * @return the string representation of the task
     * to be saved into the file.
     */
    public String toStore() {
        return this.getStatusIcon() + this.description;
    }
}
