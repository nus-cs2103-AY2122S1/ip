package duke.tasks;

/**
 * Represents a task for the user to do.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Default constructor for a task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusChar() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] :  %s", getStatusChar(), description);
    }

    /**
     * Gets the right format to store into text file.
     *
     * @return String representation of task corresponding to text file format.
     */
    public String getData() {
        int isDoneBinary = isDone ? 1 : 0;
        return String.format("%d | %s", isDoneBinary, description);
    }
}
