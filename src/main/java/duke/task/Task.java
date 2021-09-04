package duke.task;

/**
 * A {@code Task} class that creates a task with a task name
 * and {@code boolean isDone} status.
 */
public class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Initialises the {@code Task} instance with its task name.
     *
     * @param taskName Description of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Toggles completion status of task.
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Returns a {@code string} description of the task.
     *
     * @return task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a {@code boolean} value that indicates completion status of the task.
     *
     * @return Completion status of task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a {@code string} representation of a task.
     *
     * @return task name and whether it is completed.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
    }

}
