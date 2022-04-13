package duke.task;

public abstract class Task {

    protected static final String DATE_TIME_FORMAT = "E, dd MMM yyyy, HH:mm";
    private final String taskName;
    private final boolean isCompleted;

    /**
     * Creates a new basic <code>Task</code> object, which has a name and a completion status.
     * @param taskName the name of the task.
     * @see ToDo
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    protected Task(Task oldTask) {
        this.taskName = oldTask.taskName;
        this.isCompleted = true;
    }

    /**
     * Changes status of a task to 'completed'.
     * @return a new {@link duke.task.Task} exactly the same as <code>this</code> but with the
     * <code>isCompleted</code> set to true.
     * @see Event
     * @see ToDo
     * @see Deadline
     */
    public abstract Task markAsCompleted();

    /**
     * Gets the name of the task.
     * @return the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the completion status of the task.
     * @return the completion status of the task.
     */
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + this.taskName;
    }
}
