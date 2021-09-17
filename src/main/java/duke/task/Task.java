package duke.task;

/**
 * Encapsulates an abstract Task with a description and status.
 */
public abstract class Task {

    /** Description of this Task */
    private final String DESCRIPTION;

    /** Status of this task */
    private boolean isDone;

    /**
     * Constructs a Task with the given description.
     * Task is not marked as done by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [Task status] Task Description
     *
     * @return String representation of this Task, which is its status and its description.
     */
    @Override
    public String toString() {
        String statusIcon = this.isDone ? "X" : " ";
        return "[" + statusIcon + "] " + this.DESCRIPTION;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status of this Task, which is whether it has been marked as done.
     *
     * @return True if the Task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of this Task.
     *
     * @return Description of this Task.
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }
}
