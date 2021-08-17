/**
 * Encapsulates a task to be completed
 */
public class Task {
    private String description = "";
    private boolean isCompleted = false;

    /**
     * Task constructor
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks task as complete
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : "") + "] " + this.description;
    }
}
