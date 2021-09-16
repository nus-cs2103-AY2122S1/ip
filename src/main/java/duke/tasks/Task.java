package duke.tasks;

/**
 * Models the base type of various tasks.
 */

public class Task {

    /** Contains the description of the task */
    protected String description;

    /** Indicates the task's state of completion */
    protected boolean isDone;

    /**
     * Constructor to create a task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates that the given task is complete
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon of the task.
     * @return "X" if completed, otherwise empty.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Indicates that the task is completed.
     */
    public Task markAsDone() {
        return new Task(this.description, true);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
