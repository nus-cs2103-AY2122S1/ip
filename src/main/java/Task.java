public class Task {

    private boolean isDone = false;
    private final String taskName;

    /**
     * Creates a new task that is to be completed.
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks a task as completed.
     */
    public void isFinished() {
        this.isDone = true;
    }

    /**
     * A string representation of the task with its task name and its completion status.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        }
        return "[] " + this.taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}
