package duke.tasks;

/**
 * Represents a Task that user inputted.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */

public class Task {
    private String input;
    private boolean isDone;
    private String type;

    /**
     * A constructor for Task.
     *
     * @param input  User input.
     * @param isDone Status of the task.
     * @param type   Type of task.
     */
    public Task(String input, Boolean isDone, String type) {
        this.input = input;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if task is done, " " of task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status and description of the task.
     *
     * @return String representation of the task.
     */
    public String getTask() {
        return "[" + getStatusIcon() + "] " + input;
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
     * @return String representation of the done status.
     */
    public String markDone() {
        done();
        return "Nice! I've marked this task as done:\n" + getTask();
    }

    /**
     * Returns the status and description of the task when deleted.
     *
     * @return String representation of the deleted task.
     */
    public String delete() {
        return getTask();
    }

    /**
     * Returns the status of task in boolean.
     *
     * @return Boolean true if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of task.
     *
     * @return String description of task.
     */
    public String getDescription() {
        return input;
    }

    /**
     * Returns the type of task.
     *
     * @return String type of task.
     */
    public String getType() {
        return type;
    }
}
