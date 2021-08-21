public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Gets the icon representation of Task depending if Task is done.
     *
     * @return X if done, empty otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to be done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string representation with status icon and description of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    public String toDataFormat() {
        return String.format("  | %s | %s", isDone ? "1" : "0", description);
    }
}
