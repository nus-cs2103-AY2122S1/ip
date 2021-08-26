package duke.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    /**
     * Returns a string representing the status of the task.
     *
     * @return `X` if the task is already done, ` ` if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done if it is not already done.
     *
     * @return true if the task was not done previously and
     * it has been marked as done, otherwise false.
     */
    public boolean markAsDone() {
        if (!isDone) {
            isDone = true;
            return true;
        }
        return false;
    }

    /**
     * Returns the string representation of the task that will be used
     * to save to file.
     *
     * @return The string representation for file saving purposes.
     */
    public String formatForSave() {
        return String.format(" | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}