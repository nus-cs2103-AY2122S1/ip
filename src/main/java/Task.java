/**
 * Task is the base class for all tasks stored in the Duke application.
 */
public class Task {
    private static String NOT_DONE_STATUS_ICON = " ";
    private static String DONE_STATUS_ICON = "X";

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description. The task defaults to is not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the String icon that represents the status of the task.
     * @return The String icon.
     */
    public String getStatusIcon() {
        return this.isDone ? Task.DONE_STATUS_ICON : Task.NOT_DONE_STATUS_ICON;
    }

    /**
     * Converts the Task into a String that represents the Task.
     * @return The String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}