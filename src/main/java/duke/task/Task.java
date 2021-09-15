package duke.task;

/**
 * Abstract representation of a task in the duke database.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Default constructor for Task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Mark the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    private String getStatus() {
        return this.isDone ? "X" : " ";
    }


    /**
     * Convert the task into the specific string for data saving.
     *
     * @return the string representation in the specific format for database
     */
    public abstract String stringifyTask();


    /**
     * Determine if the task is identical with another task.
     *
     * @param obj the object to compare with
     * @return true if the descriptions are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return this.description.equals(task.description);
    }


    /**
     * Return the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.description);
    }
}
