package duke.information;

/**
 * Class that stores a user's task description and whether the task is done or not.
 */
public class Task {

    /** Description of the task. */
    protected String description;
    /** Boolean to check if the task has been completed. */
    protected boolean isDone;

    /**
     * Constructs Task class.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the Task.
     *
     * @return Cross if completed and blank if not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the completion status of the Task.
     *
     * @return True if task is completed or False if it is not completed.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Converts the task's information into a string.
     * To be stored in the user's dedicated txt file.
     *
     * @return String of the task information.
     */
    public String toData() {
        return (isDone ? "1|" : "0|") + this.description;
    }

    /**
     * Converts the task's information into a string.
     * To be used to display the task information to the user.
     *
     * @return String of the task information.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
