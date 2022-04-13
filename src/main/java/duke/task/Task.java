package duke.task;

/**
 * Represents of a task
 */
public abstract class Task {
    private String content;
    private boolean isDone;

    /**
     * Constructor for <code>Task</code>
     *
     * @param content content of the task
     * @param isDone true if the task is done, otherwise false
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Getter for the content of the task
     *
     * @return the content of the task
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter for isDone
     *
     * @return true if the task is done, otherwise false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Setter for isDone
     *
     * @param isDone true if you want to mark the task as done, false if vice versa
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.content);
    }
}
