/**
 * Task class to represent a task.
 *
 * @author: Chen Hsiao Ting
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a Task object with a given description.
     * @param description The description of the task input by user.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Get the status of the task.
     * @return a status in string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Print the status and description of the task.
     * @return a string representation of the task.
     */
    public String getTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Set the task to done by marking its status.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Print the status and description of the task when marked done.
     * @return a string representation of the done status.
     */
    public String markDone() {
        done();
        return "Nice! I've marked this task as done:\n" + getTask();
    }

    /**
     * Print the status and description of the task when deleted.
     * @return a string representation of the deleted task.
     */
    public String delete() {
        return getTask();
    }
}




















