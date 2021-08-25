package kermit.tasks;

/**
 * Encapsulates a task to be completed
 */
public abstract class Task {
    private String description = "";
    private boolean isCompleted = false;

    /**
     * kermit.command.Task constructor
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks task as complete
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    public boolean isComplete() {
        return this.isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getShortForm();
    /**
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getShortForm() + "] [" + (isCompleted ? "X" : "") + "] " + this.description;
    }
}
