/**
 * The Task class encapsulates a task with a String description
 * and a boolean representing if task is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task and takes in task description.
     *
     * @param description description of task.
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
        return (isDone? "X" : " "); //mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String toFileEntry();

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), description);
    }
}
