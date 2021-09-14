package models;

import java.io.Serializable;

/**
 * Task class that represents a Task that will be saved by Dub.
 */
public class Task implements Serializable {

    /** Description of the task. */
    protected String description;

    /** Boolean value that shows whether the task is done or not. */
    protected boolean isDone;

    /**
     * Constructor of the Task class.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Makes the Task done.
     */
    public void setDone() {
        this.isDone = true;
    }


    /**
     * Returns a copy of the current task.
     *
     * @return A copy of the task.
     */
    public Task copyTask() {
        Task task = new Task(description);
        task.isDone = isDone;
        return task;
    }

    /**
     * Returns true if two objects are equal.
     *
     * @param obj Object that will be compared.
     * @return True if the object are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Task) {
            Task temp = (Task) obj;
            return temp.toString().equals(this.toString());
        }

        return false;
    }

    /**
     * Returns string implementation of the Task object.
     *
     * @return String implementation of the Task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
