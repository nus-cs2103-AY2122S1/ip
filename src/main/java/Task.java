public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns the icon that represents the done state of the task */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the task as done */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /** Returns a string representation of this task to be written into data storage */
    public abstract String toDataString(String delimiter);

}
