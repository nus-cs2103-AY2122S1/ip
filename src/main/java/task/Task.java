package task;

/**
 * The Task class encapsulates a task.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

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
     * Returns task string to be written into file.
     *
     * @return string in the respective task format.
     */
    abstract public String getTaskFileString(String delimiter, String done, String notDone);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
