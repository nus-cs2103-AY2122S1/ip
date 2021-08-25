package kermit.tasks;

/**
 * Encapsulates a task to be completed.
 */
public abstract class Task {
    private String description = "";
    private boolean isCompleted = false;

    /**
     * Task constructor.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Task constructor.
     *
     * @param description Description of task.
     * @param isCompleted Boolean to set if task is completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks task as complete.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }


    /**
     * Checks if task is complete.
     *
     * @return true if task is complete, false otherwise.
     */
    public boolean isComplete() {
        return this.isCompleted;
    }

    /**
     * Gets description of task.
     *
     * @return Description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get abbreviation of task, usually first letter.
     *
     * @return String abbreviation of task.
     */
    public abstract String getShortForm();

    /**
     * Get string representation of a task including its short form and
     * completion status
     *
     * @return Stringified task.
     */
    @Override
    public String toString() {
        return "[" + getShortForm() + "] [" + (isCompleted ? "X" : "") + "] " + this.description;
    }
}
