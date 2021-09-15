package duke.task;

import static duke.io.TaskStorage.TASK_DONE;
import static duke.io.TaskStorage.TASK_NOT_DONE;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    protected boolean isDone;
    protected String name;

    /**
     * Constructor for a Task that sets its name and sets it to not done.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void doTask() {
        isDone = true;
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if task is done, false if task is not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns whether the task is expired. A task with no date is not expired.
     *
     * @return Whether the task is expired.
     */
    public boolean isExpired() {
        return false;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the task, including its status and name.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "✓" : "✗") + "] " + name;
    }

    /**
     * Returns a string compliant to the saveFile format.
     *
     * @return String to be saved as a line in save.csv.
     */
    public String getSaveString() {
        return (isDone ? TASK_DONE : TASK_NOT_DONE) + "," + name;
    }
}
