public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of the Task class
     *
     * @param description description of this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of this task
     *
     * @return string representation of this task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }
}
