package models;

import java.io.Serializable;

/**
 * Task class that represents a Task that will be saved by Dub.
 */
public class Task implements Serializable {

    /** Description of the task. */
    protected String description;

    /** Boolean value that shows whether the task is done or not. */
    protected boolean done;

    /**
     * Constructor of the Task class.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Make the Task done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Equals function that check whether 2 objects are equal or not.
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
            return temp.toString() == this.toString();
        }

        return false;
    }

    /**
     * Return string implementation of the Task object.
     *
     * @return String implementation of the Task.
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}