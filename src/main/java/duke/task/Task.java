package duke.task;

public class Task {
    private final String taskName;
    private boolean done;

    /**
     * Initialise task constructor.
     *
     * @param taskName Description of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    /**
     * Marks task as done.
     */
    public void toggleDone() {
        this.done = !this.done;
    }

    /**
     * Returns a description of the task.
     * @return task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a {@code boolean} value that indicates completion status of the task.
     * @return Completion status of task.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns a {@code string} representation of a task.
     * @return task name and whether it is completed.
     */
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.taskName;
    }
}
