package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String notes;

    /**
     * Constructs a Task class with common information.
     *
     * @param description the task description
     * @param notes additional notes that are not related to task name
     * @param completed true if task has been completed
     */
    public Task(String description, String notes, boolean completed) {
        this.description = description;
        this.isDone = completed;
        this.notes = notes;
    }

    /**
     * Returns 'X' if the task is completed, and " " otherwise.
     *
     * @return the status of the completion of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /** Marks a task as completed */
    public void markIsDone() {
        this.isDone = true;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public String getNotes() {
        return this.notes;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return the full string representation of Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);

    }

    public abstract String getType();
    public abstract String getDeadline();
}
