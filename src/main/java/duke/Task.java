package duke;

/**
 * Parent class of a task.
 */
public class Task {

    protected String description;
    protected String tag;
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
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Tags the task with a tag
     *
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Mark task as done.
     */
    public void markedAsDone() {
        isDone = true;
    }

    /**
     * Return string to save with
     */
    public String getSaveString() {
        return "";
    }

}
