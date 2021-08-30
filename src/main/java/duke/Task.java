package duke;

import java.io.Serializable;

/**
 * Represents Tasks that the user might want to do.
 * @author Nikki
 */
public class Task implements Serializable {
    protected boolean complete;
    protected String task;

    /**
     * Creates a task object.
     *
     * @param task Name of task.
     */
    public Task(String task) {
        this.task = task;
        this.complete = false;
    }

    public String getTask() {
        return this.task;
    }

    /**
     * Return the String representation of a Task.
     *
     * @return String representation of this Task.
     */
    String printTask() {
        String result;
        if (complete) {
            result = "[X] ";
        } else {
            result = "[ ] ";
        }
        return result + this.task;
    }

    void setComplete() {
        this.complete = true;
    }
}
