package jarvis;

/**
 * Class to create tasks (including deadline, event and todo tasks)
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task.
     *
     * @param description The name/description of the task
     */
    public Task(String description) {
        assert !(description.equals("")) : "Task description is empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon to indicate if a task has been completed.
     *
     * @return "[X]" if completed and "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks a given task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task as a string that is to be displayed to the user.
     *
     * @return the given task as a string that is to be displayed to the user
     */
    public String getDescription() {
        assert !(this.description.equals("")) : "Task description is empty.";
        return this.description;
    }

    /**
     * Returns the task as a string that is to be displayed to the user.
     *
     * @return the given task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Returns the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk.
     *
     * @return the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     */
    public String toPrintToFile() {
        return this.getStatusIcon() + this.description;
    }

}