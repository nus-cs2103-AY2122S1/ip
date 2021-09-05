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

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of this task to be saved in storage.
     *
     * @return the string representation of this task to be saved in storage.
     */
    public String toStringData() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    /**
     * Returns a description of this task.
     *
     * @return a description of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
