package duke;

/**
 * Parent class of a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates new task with description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the current task. "X" for done and " " for incomplete.
     *
     * @return "X" for done and " " for incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the formatted string of the task.
     *
     * @return Formatted string with the icon and description.
     */
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + getDescription();
        return s;
    }

    /**
     * Mark task as done.
     */
    public void markedAsDone() {
        isDone = true;
    }
}
