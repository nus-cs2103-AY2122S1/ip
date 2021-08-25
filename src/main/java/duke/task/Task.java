package duke.task;

/**
 * Represents a Task, which contains a Task name and a boolean variable which shows whether or not the task
 * is done.
 *
 * @author Hanif Kamal
 */
public class Task {
    private String taskName;
    private boolean doneStatus;

    /**
     * Class constructor to initialize a Task.
     * @param taskName The name or description of the Task.
     * @param doneStatus Whether or not the Task is done.
     */
    public Task(String taskName, boolean doneStatus) {
        this.taskName = taskName;
        this.doneStatus = doneStatus;
    }

    /**
     * Sets the Task as done.
     */
    public void setDone() {
        this.doneStatus = true;
    }

    /**
     * Returns the string representation of the Task.
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        return this.doneStatus ? "[X] " + this.taskName : "[] " + this.taskName;
    }
}