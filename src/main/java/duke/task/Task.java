package duke.task;

/**
 * Represents a Task, which contains a Task name and a boolean variable which shows whether or not the task
 * is done.
 *
 * @author Hanif Kamal
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Class constructor to initialize a Task.
     *
     * @param taskName The name or description of the Task.
     * @param isDone Whether or not the Task is done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Sets the Task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the Task name.
     *
     * @return String representation of the Task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.taskName : "[] " + this.taskName;
    }
}
