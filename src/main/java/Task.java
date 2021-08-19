/**
 * Represents a task, which is contained in a list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a Task.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon to indicate whether the Task is done.
     *
     * @return "X" if the Task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return An icon indicating whether the Task is done and the description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}