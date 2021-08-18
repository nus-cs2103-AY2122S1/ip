/**
 * This class represents tasks to be done by the user.
 */
public abstract class Task {
    // The name of the task.
    protected String taskName;
    // To track if the task is done.
    protected boolean isDone;

    /***
     * Constructor to create a task.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /***
     * To mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /***
     * Returns the string representation of the task.
     *
     * @return The name of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + taskName;
    }
}
