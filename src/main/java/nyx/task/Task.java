package nyx.task;

/**
 * Represents a task.
 * Contains information about the task and whether it is marked as done.
 */
public abstract class Task {
    private String content;
    private boolean isDone;

    /**
     * Constructor for Task. (For invocation by subclass constructors).
     *
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

    String getContent() {
        return this.content;
    }

    /**
     * Returns a String representation of the task in the format required for saving into hard disk.
     *
     * @return String representation of the task in the format required to save into text hard disk.
     */
    public abstract String formatData();

    /**
     * Marks task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Changes the content of the task.
     *
     * @param newContent The new content to change to.
     */
    public void updateContent(String newContent) {
        this.content = newContent;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}
