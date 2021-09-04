package duke.tasks;

/**
 * Encapsulates information of a Task object than contains the description and completion status.
 */

public class Task {
    // fields of each task
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object with the specified task description and task status.
     *
     * @param description Description/Tile of the task.
     * @param isDone Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if the task is completed and returns the corresponding icon.
     *
     * @return A String representing the status of the task.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Marks the task has been completed.
     */
    public void markAsComplete() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns the tag of the task.
     * Indicates which type of task is it.
     *
     * @return A String represent the type of task.
     *         An empty String is the type if not recognised by the chat bot.
     */
    public String getTag() {
        return "";
    }

    /**
     * Returns the description/title of the task.
     *
     * @return A String representing the description/title of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task to the new description.
     *
     * @param desc New description of the task
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Sets the date of the task to the new date if it is a Deadline task or Event task.
     * Otherwise, do nothing.
     *
     * @param date New date of the task
     */
    public void setDate(String date) { }

    /**
     * Returns the due date of the task if it belongs to the Deadline/Event type.
     * Otherwise, return a string stating no data, as there is no due that for this task type.
     *
     * @return A String indicating that there is not due date if it does not exist.
     *         A String indicating the due date if it exists.
     */
    public String getDueDate() {
        return "No Data";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
