package duke;

/**
 * Represents a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /** Mark task as done. */
    public void markDone() {
        this.isDone = true;
    }

    /** Mark task as not done. */
    public void markNotDone() {
        this.isDone = false;
    }

    /** Convert task object into string.
     *
     * @return string that contains the data of the task
     */
    protected String convertToSavableString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString()
    {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}