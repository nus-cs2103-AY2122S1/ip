package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * The {@code Task} parent class contains abstractions for tasks.
 */
public abstract class Task {
    protected static final DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");
    protected String description;
    protected boolean isDone = false;

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
     * Marks the current {@code Task} to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description of {@code Task}.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String checkbox = isDone ? "[X]" : "[ ]";
        return checkbox + " " + description;
    }
}
