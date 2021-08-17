/**
 * Class encapsulating a task.
 */
public class Task {
    /**
     * String to describe the task.
     */
    private String description;

    /**
     * boolean to keep track if task is completed.
     */
    private boolean isCompleted;

    /**
     * Public constructor for creating a task.
     *
     * @param description Description of the task created.
     */
    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    /**
     * Method to mark task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * String representation of the task.
     *
     * @return String The string describing the task.
     */
    @Override
    public String toString() {
        char marked = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", marked, this.description);
    }
}
