package retriever.task;

/**
 * This class would help to keep track of tasks.
 */
public class Task {
    /** Variables to hold values. */
    private String description;
    private boolean isDone;

    /**
     * Types of tasks present.
     */
    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

    /**
     * Initializes a task.
     *
     * @param description The task description entered by the user.
     */
    public Task(String description) {
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
        return description;
    }

    /**
     * Sets the task to done by updating the isDone variable.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the task description and its status in an
     * organised format.
     *
     * @return A String including the task details.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
