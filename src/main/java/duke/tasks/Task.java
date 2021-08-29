package duke.tasks;

/**
 * Abstract class for all types of tasks that Duke can handle.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task (String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Checks if this task has been completed.
     *
     * @return True if the task has been completed, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the name of this task.
     *
     * @return Name of this task.
     */
    public String taskName() {
        return this.taskName;
    }

    public abstract String fileSaveFormat();

    /**
     * Returns the tag used to identify the type of this task.
     *
     * @return String representation of the tag associated with this task.
     */
    public static String taskTag() {
        //TODO change to exception
        return "Invalid Task";
    };

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", taskName);
    }

}
