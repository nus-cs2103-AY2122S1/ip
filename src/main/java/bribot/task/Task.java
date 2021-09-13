package bribot.task;

/**
 * Represents an abstract task.
 */

public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that sets the description of the task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * returns 'X' if the task is marked as done.
     * @return 'X' if the task is done, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = !isDone;
    }

    /**
     * Returns the type of task to be written into the text file during saving.
     * @return String that represents the type of task.
     */
    public abstract String getType();

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public abstract String getDescription();

    /**
     * Returns the date and time of the task if applicable.
     * @return the date and time of the task if applicable.
     */
    public abstract String getDateTimeString();

    /**
     * Returns the string representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public abstract int compareTo(Task o);
}
