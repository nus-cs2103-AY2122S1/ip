package duke;

/**
 * The Task.
 */
public class Task {
    /** The task description */
    protected String description;

    /** The task status. True if it is done, else false */
    protected boolean isDone;

    /**
     * Constructor for a Task.
     * @param description The new task description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a string for the task status icon.
     * @return "X" if the test is done, else " ".
     */
    public String getStatusIcon() {
        return isDone
                ? "X"
                : " ";
    }

    /**
     * Returns the string representation of a Todo task in the saved file.
     * @return The string representation of a Todo task in the saved file.
     */
    public String savedToString() {
        String doneStatus = isDone ? "1" : "0";
        return "T | " + doneStatus + " | " + this.description;
    }

    /**
     * Returns the string representation of a task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
