package duke;

import java.io.Serializable;

/**
 * A Task class which provides the basic skeleton for what a Task should have.
 */

public class Task implements Serializable {
    private boolean isDone;
    private String title;

    /**
     * Constructor.
     * @param title the title of the task.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;

    }

    /**
     * Method to set the status of the Task to done.
     * @param status the value that done is to be set to.
     */
    public void setDone(boolean status) {
        isDone = status;
        System.out.println("Nice! I've marked this task as done:\n[X]" + this.title);
    }

    /**
     * Retrieve the status of the task (done or not).
     * @return a boolean.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Method that returns the title of the Task.
     * @return a String.
     */
    public String getTitle() {
        return this.title;
    }


    /**
     * Overriden toString method.
     * @return a String.
     */
    @Override
    public String toString() {
        if (!this.isDone) {
            return "[ ] " + this.title;
        } else {
            return "[X] " + this.title;
        }

    }
}
