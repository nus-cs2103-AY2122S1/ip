package duke;

/**
 * Represents a task that has been added by the user to the application.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor without boolean isDone
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor with boolean isDone
     *
     * @param description
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns true if task is completed and false otherwise.
     *
     * @return value of isDone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns status icon for user visualisation. Cross represents completed task.
     *
     * @return "X" if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets status of a task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        return "|" + this.getStatusIcon() + "| " + this.description;
    }
}
