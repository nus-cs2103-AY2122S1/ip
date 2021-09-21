package duke;

/**
 * This class encapsulates a Task.
 *
 * @author Kleon Ang
 */
public abstract class Task {
    private static final String DATA_DELIMITER = " | ";
    private final String description;
    private boolean isDone;
    private Priority priority;

    private enum Priority {
        LOW, MEDIUM, HIGH
    }

    /**
     * Constructor for a Task.
     *
     * @param description A string describing the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    private String getStatusIcon() {
        String doneStatus = "X";
        String undoneStatus = " ";
        return (isDone ? doneStatus : undoneStatus); // mark done task with X
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
        return this.getPriority() + DATA_DELIMITER + status + DATA_DELIMITER + this.description;
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
     * Sets this task's priority to the given priority.
     *
     * @param priority A Priority (LOW, MEDIUM or HIGH)
     */
    public void setPriority(String priority) throws DukeException {
        switch (priority) {
        case "LOW":
            this.priority = Priority.LOW;
            break;
        case "MEDIUM":
            this.priority = Priority.MEDIUM;
            break;
        case "HIGH":
            this.priority = Priority.HIGH;
            break;
        default:
            throw new DukeException("Please specify either 'low', 'medium' or 'high' priority.");
        }
    }

    /**
     * Gets a string representation of this task's priority level.
     *
     * @return A string representation of this task's priority level.
     */
    public String getPriority() {
        return this.priority.toString();
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string including the Task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getPriority() + "][" + this.getStatusIcon() + "] " + this.description;
    }
}
