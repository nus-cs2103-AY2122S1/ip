package duke;

/**
 * This class encapsulates a Task.
 *
 * @author Kleon Ang
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description A string describing the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a data string of the Task for saving.
     *
     * @return A data string including the Task's status icon and description.
     */
    public String getDataString() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.description;
    }

    /**
     * Checks if this task's description contains a given keyword.
     *
     * @param keyword A string keyword to check.
     * @return True if this task's description contains the given keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string including the Task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
