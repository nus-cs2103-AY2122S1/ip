package duke.task;

/**
 * Represents the abstract concept of Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of a Task.
     *
     * @return Description String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Determines if the Task is Done.
     *
     * @return true or false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the Status Icon of a Task.
     *
     * @return "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }


    /**
     * Marks a Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
