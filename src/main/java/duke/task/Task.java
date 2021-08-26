package duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    //Description of Task
    protected String description;
    //Flag for whether a task is done, default false
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description String description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public String getDescription() {
        return this.description;
    }


    public void setDone() {
        this.isDone = true;
    }

    /**
     * toString method for Task, showing the status of completion and description.
     *
     * @return Status of completion and description of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
