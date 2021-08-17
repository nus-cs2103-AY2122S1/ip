public class Task {
    protected static int count = 0;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.count++;
    }

    /**
     * Return icon indicating status of this task
     * @return X if done, empty if otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Mark this task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of this task
     * @return string representation of task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s",this.getStatusIcon(), this.description);
    }
}
