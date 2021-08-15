/**
 * Task class represents a task that Duke keep tracks of and it contains the description and status of it.
 *
 * @author Chng Zi Hao
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Name of user's task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the String representation of Task's status.
     *
     * @return a String where "X" represent completed and " " represents uncompleted.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Change status of task such that is is mark as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
