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
