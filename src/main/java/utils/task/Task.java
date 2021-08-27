package utils.task;

/**
 * The Task class encapsulates a task.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a new components.Task object.
     * By default created tasks are not done.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     * @return "X" for done or " " for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get task string to be inputted into file.
     * @return string in the format <task><delimiter><done><delimiter><datetime>
     */
    abstract public String getTaskFileString(String delimiter, String done, String notDone);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
