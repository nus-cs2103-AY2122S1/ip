package duke.task;

/**
 * Abstract class task contains fundamental attributes such as task name
 * and whether task is completed.
 */
public abstract class Task {
    private final String taskName;
    private boolean done;

    /**
     * Task constructor initialises Task with completed status and
     * task name.
     *
     * @param done task is completed.
     * @param taskName name of task.
     */
    protected Task(boolean done, String taskName) {
            this.taskName = taskName;
            this.done = done;
        }

    /**
     * Sets done to true.
     */
    protected void markAsDone() {
        this.done = true;
    }

    /**
     * Transform a task into a concise string that contains information about the task.
     *
     * @return concise string representation of task to be saved into memory
     */
    protected String encode() {
        return done
                ? String.format("1|%s", this.taskName)
                : String.format("0|%s", this.taskName);
    }

    /**
     * Turn task into a human readable string form.
     *
     * @return human readable string representation of task.
     */
    @Override
    public String toString() {
        return done
                ? String.format("[X] %s", this.taskName)
                : String.format("[ ] %s", this.taskName);
    }
}
