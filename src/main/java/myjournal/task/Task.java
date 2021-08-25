package myjournal.task;

/**
 * An abstract class for tasks.
 *
 * @author Felissa Faustine
 */
public abstract class Task {
    public String taskName;
    public boolean isDone;

    /**
     * This is the constructor for the Task class.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

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
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStateIcon() + "] " + taskName;
    }

    public abstract String getTime();
}
