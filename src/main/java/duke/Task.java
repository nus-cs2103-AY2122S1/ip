package duke;

/**
 * Implements a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructs a Task object.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Constructs a Task object.
     * @param description Description of the task.
     * @param tag Tag associated to the task.
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Gets the status of completion of the task.
     * @return A string representing whether the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     * @return A string representation of the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the task's tag.
     * @return A string representation of the tag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns a string representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
