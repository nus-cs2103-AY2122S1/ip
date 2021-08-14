class Task {
    /**
     * To indicate if a task is done.
     */
    public boolean isDone = false;
    private final String description;

    /**
     * Constructor for a task.
     *
     * @param description description for the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }
}