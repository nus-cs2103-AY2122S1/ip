package tasks;

/**
 * tasks.Task class to represent a task.
 *
 * @author: Chen Hsiao Ting
 */

public class Task {
    private String description;
    private boolean isDone;
    private String type;

    /**
     * A constructor for the task.
     *
     * @param description user input task description.
     * @param isDone status of the task.
     * @param type type of task.
     */
    public Task(String description, Boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the status of the task.
     *
     * @return a status in string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status and description of the task.
     *
     * @return a string representation of the task.
     */
    public String getTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Sets the task to done by marking its status.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Returns the status and description of the task when marked done.
     *
     * @return a string representation of the done status.
     */
    public String markDone() {
        done();
        return "Nice! I've marked this task as done:\n" + getTask();
    }

    /**
     * Returns the status and description of the task when deleted.
     *
     * @return a string representation of the deleted task.
     */
    public String delete() {
        return getTask();
    }

    /**
     * Returns the status of task.
     *
     * @return boolean status of task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of task.
     *
     * @return string description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of task.
     *
     * @return string type of task.
     */
    public String getType() {
        return type;
    }
}