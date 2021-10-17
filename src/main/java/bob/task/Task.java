package bob.task;

/**
 * Represents a task for the user to ask Bob to store in the list and complete or remove at a later time.
 */
public class Task {
    /** Description of user Task */
    private String description;

    /** Whether the Task has been completed by the user */
    private boolean isCompleted;

    /**
     * Constructor for a new Task instance.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        assert !description.isEmpty();
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Checks if the current Task is completed.
     *
     * @return "X" if the task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Marks the current Task as completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns the String representation of the current Task.
     *
     * @return String representation of the current Task.
     */
    public String printTask() {
        assert (this.getStatusIcon() == "X" || this.getStatusIcon() == " ");
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
