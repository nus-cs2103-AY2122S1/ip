package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructs a new Task object.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructs a new Task object.
     *
     * @param description Task description.
     * @param isCompleted Task completion status.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String statusIcon = this.isCompleted ? "X" : " ";
        return "[" + statusIcon + "] " + this.description;
    }
}
