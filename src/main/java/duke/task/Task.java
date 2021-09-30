package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task status
     * @return string representation of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task
     * @return description of the task
     */
    public String getDesription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task in the text file
     * @return string representation of the task in the text file
     */
    public abstract String addToFile();

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task to be shown in the Duke app
     * @return the string representation of the task to be shown in the Duke app
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDesription();
    }
}
