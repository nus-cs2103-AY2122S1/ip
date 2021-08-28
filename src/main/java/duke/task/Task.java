package duke.task;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    public final static String STORAGE_DELIMITER = "%";

    private String description;
    private boolean isDone;

    /**
     * Creates a task.
     *
     * @param description Task's description.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task.
     *
     * @param description Task's description.
     * @param isDone Status of task's completion.
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a status icon representing whether task has been marked done.
     *
     * @return string status icon representing whether task has been marked done.
     */
    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns string representation of task for storage on disk.
     *
     * @return string representation of task for storage on disk.
     */
    public String toStorageFormat() {
        return description + STORAGE_DELIMITER + isDone;
    }
}
