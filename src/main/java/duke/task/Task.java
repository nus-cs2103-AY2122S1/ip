package duke.task;

/** A class to encapsulate the tasks
 * which user inputs.
 */
public class Task {

    /** The description of the task */
    protected String description;

    /** The boolean indicating if the task is done */
    protected boolean isDone;

    /**
     * A public constructor for Task which initializes
     * the description to the given one.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the icon indicating if the task is done.
     * X indicates that the task is done.
     *
     * @return The string representing the status of the task.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representing the task type.
     *
     * @return The string containing "Task"
     */
    public String getTaskIndicator() {
        return "Task";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representing the task.
     */
    public String toString() {

        return "[" + getStatusIcon() + "] " + this.description;
    }

}
