package duke.task;

/**
 * Abstract class Task that superclasses all Tasks.
 */
public abstract class Task {

    /** Description String of the Task. */
    private String description;
    /** Boolean that determines whether a task is done or not. */
    private boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description for a task.
     */
    public Task(String description) {
        assert description != null : "Task description cannot be null.";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the description of the task.
     *
     * @param newDescription new description of the task.
     */
    public void updateDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * Gets status of the task (whether it is done or not).
     *
     * @return Boolean.
     */
    protected boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String result = this.isDone ? "[X] " : "[ ] ";
        result += this.description;
        return result;
    }

    /**
     * Returns a string to be used for saving.
     *
     * @return string with delimiters for saving and loading.
     */
    public abstract String toDatabaseString();
}
