package bob.task;

/**
 * Represents a task for the user to ask Bob to store in the list and complete or remove at a later time.
 */
public class Task {
    private String description;
    private boolean completed;

    /**
     * Constructor for a new Task instance.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Checks if the current Task is completed.
     *
     * @return "X" if the task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (completed ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the current Task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns the String representation of the current Task.
     *
     * @return String representation of the current Task.
     */
    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
