package duke.task;

import java.time.LocalDate;

/**
 * Tasks associated with a description, and whether it has been completed.
 */
public class Task {

    /**
     * The description associated with the task
     **/
    protected String description;

    /**
     * Indicates whether the task has been done or not
     **/
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description The description associated with the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Prints whether the task is done or not.
     *
     * @return If task is done, returns "X", else returns ""
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks a given task as done.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Gets the done status of the task.
     *
     * @return true if task is done, else false.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description associated with the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return null
     */
    public String getTaskType() {
        return null;
    }

    /**
     * Returns the timing of the task.
     *
     * @return null
     */
    public LocalDate getTiming() {
        return null;
    }

    /**
     * Pretty-prints the task in a readable way.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
