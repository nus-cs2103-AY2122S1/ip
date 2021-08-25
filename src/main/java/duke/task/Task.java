package duke.task;

/**
 * Encapsulates a task with a description and whether has it been completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Public constructor to initialize a Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done with a "X", if it is not done it will be just a blank space.
     *
     * @return A string showing whether a task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Returns the output of deadline in a format suitable for saving to a file.
     *
     * @return A string representing the output.
     */
    public String outputFormat() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    //...
}
