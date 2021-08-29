package duke;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Generic task that stores a description and whether the task is completed.
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Changes task completion state to true.
     */
    public void complete() {
        this.isDone = true;
    }
}
