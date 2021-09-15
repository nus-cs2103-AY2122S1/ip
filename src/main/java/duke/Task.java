package duke;

import java.io.Serializable;

/**
 * Represents Tasks that the user might want to do.
 * @author Nikki
 */
public class Task implements Serializable {
    static final String DONE = "[X] ";
    static final String NOT_DONE = "[ ] ";
    protected boolean isComplete;
    protected String task;

    /**
     * Creates a task object.
     *
     * @param task Name of task.
     */
    public Task(String task) {
        this.task = task;
        this.isComplete = false;
    }

    /**
     * Return the String representation of a Task.
     *
     * @return String representation of this Task.
     */
    String printTask() {
        String result;
        if (isComplete) {
            result = DONE;
        } else {
            result = NOT_DONE;
        }
        return result + task;
    }

    void setComplete() {
        isComplete = true;
    }
}
