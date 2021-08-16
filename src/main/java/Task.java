/**
 * This class would help to keep track of tasks.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor, to initialize a task.
     *
     * @param description The task description entered by the user.
     */
    public Task(String description) {

        /** Variables to hold values. */
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task in the form of a String.
     *
     * @return A String indicating whether the task is accomplished or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task to done by updating the isDone variable.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task description and its status in an
     * organised format.
     *
     * @return A String including the task details.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
