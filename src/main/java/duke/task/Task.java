package duke.task;

/**
 * Represents a task in the Duke program.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description Description of this task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of this task to be saved in storage.
     *
     * @return the string representation of this task to be saved in storage.
     */
    public String toStringData() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a description of this task.
     *
     * @return a description of this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
