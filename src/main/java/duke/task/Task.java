package duke.task;

import java.io.Serializable;

/**
 * Represents a Task to be done.
 */
public class Task implements Serializable {
    //Description of Task
    protected String description;
    //Flag for whether a task is done, default false
    protected boolean isDone;

    /**
     * Constructs a Task with a String description.
     *
     * @param description String description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Converts Task to String, showing the status of completion and description.
     *
     * @return Status of completion and description of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
