package jarvis.task;

/**
 * Encapsulates a Task.
 */
public class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Constructor for Task.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * String representation of a task that is to be saved to storage file.
     *
     * @return String representation that is to be saved.
     */
    public String toStorageFormatString() {
        return String.format("%s;;;%s", isDone ? "1" : "0", description);
    }
}
