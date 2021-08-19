public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the task (whether is it done)
     * @return Status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get description of the task
     * @return decription of task
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String fullDescription = String.format("[%s] %s", getStatusIcon(), getDescription());
        return fullDescription;
    }


}
