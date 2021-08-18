/**
 * Represents a task object.
 */
public abstract class Task {
    /** The description of the task */
    protected String description;

    /** The status of the task */
    protected boolean isDone;

    /** Task constructor */
    protected Task() {
        this("");
    }
    
    /**
     * Task constructor.
     *
     * @param description the description of the task
     */
    protected Task(String description) {
        this(description, false);
    }

    /**
     * Task constructor.
     *
     * @param description the description of the task
     * @param isDone the status of the task
     */
    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Marks the task's status as done */
    public Task markTaskAsDone() {
        isDone = true;
        return this;
    }
}
