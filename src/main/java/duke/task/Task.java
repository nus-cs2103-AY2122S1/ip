package duke.task;

/**
 * This is the Task class that contains the properties
 * of a task.
 */
public abstract class Task {
    private final String name;
    private final boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param name Task name.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a Task object.
     *
     * @param name Task name.
     * @param isDone Task status: done or not done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns status of task.
     *
     * @return Task status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns task with done status.
     *
     * @return Task with done status.
     */
    public abstract Task markAsDone();

    /**
     * Returns name of task.
     *
     * @return Task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Formats Task to String array.
     *
     * @return Task in String array.
     */
    public abstract String[] formatTask();

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Prints task.
     * If task is done, [X] Task1; else, [ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
