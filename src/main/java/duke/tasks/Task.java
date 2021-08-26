package duke.tasks;

/**
 * The {@code Task} parent class contains abstractions for tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public enum TaskTypes {
        TODO, DEADLINE, EVENT
    }

    public static int numOfTasks = 0;

    /**
     * Initializes an instance of {@code Task} with {@code String description}.
     *
     * @param description {@code String} that contains description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Initializes an instance of {@code Task} with {@code String description}
     * and {@code boolean isDone} status.
     *
     * @param description {@code String} that contains description of task.
     * @param isDone {@code boolean} that represents task completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the current task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String checkbox = isDone ? "[X]" : "[ ]";
        return checkbox + " " + description;
    }
}
