package myjournal.task;

/**
 * An abstract class for tasks.
 *
 * @author Felissa Faustine.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs the Task object.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Sets the state of the task.
     *
     * @param state The new state;
     */
    public void setState(boolean state) {
        this.isDone = state;
    }

    /**
     * Gets the state of the task.
     *
     * @return A boolean representing whether the task is done or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the symbol of the task in form of a string.
     *
     * @return The symbol of the task in form of a string.
     */
    public abstract String getSymbol();

    /**
     * Gets the state icon of the task.
     *
     * @return The state icon of the task.
     */
    public String getStateIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the time of the task.
     *
     * @return The time of the task.
     */
    public abstract String getTime();

    /**
     * Sets the time of the task.
     *
     * @param time The time of the task.
     */
    public abstract void setTime(String time);

    /**
     * Sets the description of the task.
     *
     * @param taskName The description of the task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStateIcon() + "] " + taskName;
    }
}
