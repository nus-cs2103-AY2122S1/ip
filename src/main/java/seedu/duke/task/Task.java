package seedu.duke.task;

/**
 * Represents a task. A <code>Task</code> is described by
 * a string description.
 */
public class Task {
    /**
     * String to describe the <code>Task</code> object.
     */
    protected String description;

    /**
     * Boolean to keep track if <code>Task</code> object
     * is completed.
     */
    private boolean isCompleted;

    /**
     * Public constructor for creating a <code>Task</code> object.
     *
     * @param description Description of the <code>Task</code> object created.
     */
    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    /**
     * Public constructor for creating a <code>Task</code> object.
     *
     * @param description Description of the <code>Task</code> object created.
     * @param isCompleted Boolean indicating if task is completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted =isCompleted;
    }

    /**
     * Check if the task description contains the given keyword.
     *
     * @param keyword To be searched for.
     * @return true if the description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Mark <code>Task</code> object as completed.
     */
    public Task markAsCompleted() {
        return new Task(this.description, true);
        // this.isCompleted = true;
    }

    /**
     * String representation of the <code>Task</code> object.
     *
     * @return String The string describing the <code>Task</code> object
     */
    @Override
    public String toString() {
        char marked = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", marked, this.description);
    }
}
