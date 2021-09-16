package duke.task;

/**
 * Task class which encapsulates task description and status of completion.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns an icon to indicate completion status of task.
     *
     * @return "X" or " " depending on status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Formats task's data into a string for storage in duke.txt.
     *
     * @return String containing task's data.
     */
    public String getData() {
        return (getIsDone() ? 1 : 0) + " // " + getDescription();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string describing details of the task.
     *
     * @return A String describing details of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
