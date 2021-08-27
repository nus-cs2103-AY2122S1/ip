package main.java.task;

/**
 * Task is the superclass of classes to be stored in the database.
 * All subclasses require a description field and have an isDone field that indicates completion status.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor of superclass Task.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Gets the status of the current task object
     * @return the string status where X means done and blank is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done by changing the isDone boolean to true
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the description of this task.
     * @return the string description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}