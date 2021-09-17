package Wonderland.task;

/**
 * The Wonderland.Wonderland.task.Task class encapsulates a Wonderland.Wonderland.task with a String description
 * and a boolean representing if Wonderland.Wonderland.task is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Wonderland.Wonderland.task and takes in Wonderland.Wonderland.task description.
     *
     * @param description description of Wonderland.Wonderland.task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X if isDone true.
     *
     * @return "X" if isDone true.
     */
    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    /**
     * Marks Wonderland.Wonderland.task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public abstract String toFileEntry();

    /**
     * Returns string representation of Wonderland.Wonderland.task object.
     *
     * @return string representation of Wonderland.Wonderland.task object.
     */
    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), description);
    }
}
