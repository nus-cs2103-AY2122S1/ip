package duke.task;

/**
 * This Task class implements the characteristics of a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String tag;
    protected boolean isTagged;

    /**
     * Constructor for a Task instance that takes in a description.
     * The task is initially not done.
     *
     * @param description The given task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates if the task has been completed.
     *
     * @return A boolean value indicating the completion status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an icon, indicating if the current task has been done.
     *
     * @return A String representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Labels the task.
     *
     * @param tag The specified tag name.
     */
    public void setTag(String tag) {
        this.tag = tag;
        isTagged = true;
    }

    /**
     * Returns the attached label of the task.
     * If task is not tagged, return an empty String.
     *
     * @return The label of the task.
     */
    public String getTag() {
        if (isTagged) {
            return tag;
        } else {
            return " ";
        }
    }

    /**
     * Returns the string representation of a Task instance.
     *
     * @return A string representing a Task instance.
     */
    @Override
    public String toString() {
        if (isTagged) {
            return "[" + getStatusIcon() + "][#" + tag + "] " + description;
        } else {
            return "[" + getStatusIcon() + "][ ] " + description;
        }
    }
}
