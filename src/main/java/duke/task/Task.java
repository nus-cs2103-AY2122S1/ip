package duke.task;

/**
 * Represents a Task object. It is an abstract class.
 */
public abstract class Task {
    private boolean isDone = false;

    public abstract String showTask();
    public abstract String showTaskOnly();
    public abstract String showType();
    public abstract String showWhen();

    /**
     * Returns an indication of whether the task is done or not.
     *
     * @return "[X]" indicating the task is done and "[ ]" if not done
     */
    public String checkDone() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Sets the done status of task to true
     */
    public void isDone() {
        isDone = true;
    }
}
