package Duke.task;

/**
 * The Duke.Duke.task.Task class encapsulates a Duke.Duke.task with a String description
 * and a boolean representing if Duke.Duke.task is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Duke.Duke.task and takes in Duke.Duke.task description.
     *
     * @param description description of Duke.Duke.task.
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
     * Marks Duke.Duke.task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public abstract String toFileEntry();

    /**
     * Returns string representation of Duke.Duke.task object.
     *
     * @return string representation of Duke.Duke.task object.
     */
    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), description);
    }
}
