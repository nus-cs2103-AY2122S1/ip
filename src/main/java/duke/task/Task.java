package duke.task;

/**
 * Represents tasks that helps users to keep track of what needs to be done.
 *
 * @author botr99
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task with the corresponding description,
     * and whether it is done or not done.
     *
     * @param description Description of the task.
     * @param isDone True if task is done; false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of a task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets a task to be done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string indicating whether a task is done,
     * followed by the description of the task.
     *
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns a string indicating whether a task is done,
     * followed by the description of the task.
     *
     * @return The string representation of a task to be stored in storage
     *         under the user's hard disk.
     */
    public String toStorageString() {
        return (isDone ? "1" : "0") + " | " + description;
    };

    /**
     * Checks if a task is equal to another task.
     *
     * @param obj The other task to be compared with.
     * @return True if both tasks share the same description and done status;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return description.equals(other.description)
                && isDone == other.isDone;
    }
}
