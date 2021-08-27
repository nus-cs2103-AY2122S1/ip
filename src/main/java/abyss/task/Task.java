package abyss.task;

/**
 * Represents an general task with a description and whether it is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getIsDone() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns formatted details of the task.
     *
     * @return Formatted task details.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns details of the task formatted for file entry.
     *
     * @return Formatted task details.
     */
    abstract public String toFileEntry();
}
