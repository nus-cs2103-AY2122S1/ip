package seedu.duke.task;

/**
 * Class encapsulating a duke.task.
 */
public class Task {
    /**
     * String to describe the duke.task.
     */
    private String description;

    /**
     * boolean to keep track if duke.task is completed.
     */
    private boolean isCompleted;

    /**
     * Public constructor for creating a duke.task.
     *
     * @param description Description of the duke.task created.
     */
    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    /**
     * Method to mark duke.task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * String representation of the duke.task.
     *
     * @return String The string describing the duke.task.
     */
    @Override
    public String toString() {
        char marked = isCompleted ? 'X' : ' ';
        return String.format("[%c] %s", marked, this.description);
    }
}
