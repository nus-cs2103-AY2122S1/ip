package task;

/**
 * The Task class encapsulates a task.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a new components.Task object.
     * By default created tasks are not done.
     *
     * @param description
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status of the task.
     *
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
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Get task string to be inputted into file.
     *
     * @return string in the respective task format.
     */
    abstract public String getTaskFileString(String delimiter, String done, String notDone);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
