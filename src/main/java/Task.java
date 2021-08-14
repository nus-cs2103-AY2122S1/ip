public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the icon representation of Task depending if Task is done.
     *
     * @return X if done, empty otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string representation with status icon and description of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
