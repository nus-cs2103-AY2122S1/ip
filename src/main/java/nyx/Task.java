package nyx;

/**
 * Represents a task.
 * Contains information about the task and whether it is marked as done.
 */
public abstract class Task {
    private final String content;
    private boolean isDone;

    /**
     * Constructor for Task. (For invocation by subclass constructors)
     * @param content Description of the task.
     * @param isDone Indicator to indicate whether the task is done.
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected int getStatusInt() {
        return isDone ? 1 : 0;
    }

    protected String getContent() {
        return this.content;
    }

    /**
     * Returns a String representation of the task in the format required for saving into hard disk.
     * @return String representation of the task in the format required to save into text hard disk.
     */
    public abstract String dataFormat();

    /**
     * Mark task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}