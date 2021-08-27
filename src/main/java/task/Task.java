package task;

public class Task {

    /** Text description of Task */
    protected String description;
    /** Boolean value whether Task is done */
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description Text description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether the current Task is done.
     * @return True if Task is done, false otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark current Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Override toString method to show representation of Task.
     * @return String representing the Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Get text description of Task.
     * @return Text description of Task
     */
    public String getDescription() {
        return this.description;
    }

}
