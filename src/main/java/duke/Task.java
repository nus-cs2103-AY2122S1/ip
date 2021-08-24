package duke;

/**
 * Encapsulates a task, which can be entered into the to-do-list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description A short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a task.
     *
     * @param description A short description of the task.
     * @param isDone A boolean to indicate whether the task is already done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a character indicating whether the task is done or not.
     *
     * @return Icon 'X' if the task is done, ' ' whitespace otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task, in a format suitable for storing in a text file.
     *
     * @return The string representation of the task, in a format suitable for storing in a text file.
     */
    public String toStringForFile() {
        String status = (this.isDone) ? "1" : "0";
        return status + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
