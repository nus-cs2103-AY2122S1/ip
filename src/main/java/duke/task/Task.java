package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract class Task that superclasses all Tasks.
 */
public abstract class Task {

    /** The formatting patterns for Tasks with date (and time) inputs. */
    protected final DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    /** The formatting patterns for Tasks with date inputs. */
    protected final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MMM d yyyy");
    /** Description String of the Task */
    private final String description;
    /** Boolean that determines whether a task is done or not */
    private boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description for a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets description of the task.
     *
     * @return the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets status of the task (whether it is done or not).
     *
     * @return true if the task is marked done, false otherwise.
     */
    protected boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String result = this.isDone ? "[X] " : "[ ] ";
        result += this.description;
        return result;
    }

    /**
     * Returns a string to be used for saving.
     *
     * @return string with delimiters for saving and loading.
     */
    public abstract String databaseString();
}
