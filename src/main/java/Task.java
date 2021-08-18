/**
 * The Task class encapsulates a task.
 */
public class Task {
    /** The task to do. */
    protected String description;
    /** Boolean to represent if the task is done, true if done, false otherwise. */
    protected boolean isDone;

    /**
     * Constructor for initialising a Task.
     * @param description The task to do.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to check and return the status of a task in the form of "X" or " ".
     * @return If the task is done "X" will be returned, else " " will be returned.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }
}
