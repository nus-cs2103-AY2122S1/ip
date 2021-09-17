package duke;

/**
 * Class for tasks in Duke.
 *
 * @author Samuel Lau
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status icon of the task.
     *
     * @return String representation ot the icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string in the written format
     * to be saved in the text file.
     *
     * @return String to be saved.
     */
    public String toWrite() {
        return "";
    }

    /**
     * Returns the string representation of the task object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}