package main.java;


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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}