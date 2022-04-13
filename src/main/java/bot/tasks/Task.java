package bot.tasks;

/**
 * Represents a task.
 */
public class Task {
    protected static final String YMD_DATE_FORMAT = "yyyy/MM/dd";
    protected static final String DMY_DATE_FORMAT = "dd/MM/yyyy";
    protected static final String YMD_REGEX = "([0-9]{4})/([1-9]|[0][1-9]|[1][0-2])/"
            + "([1-9]|[0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])";
    protected static final String DMY_REGEX = "([1-9]|[0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])"
            + "/([1-9]|[0][1-9]|[1][0-2])/([0-9]{4})";
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description.
     *
     * @param description The description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return A string representing the description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of this task.
     *
     * @return A string representing the status of this task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representing the task that is shown to the user and is overridden by subclasses.
     *
     * @return A String representing this task.
     */
    public String toListFormat() {
        return "";
    }

    /**
     * Returns this task's status and description.
     *
     * @return A string representing the status of this task and its description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
