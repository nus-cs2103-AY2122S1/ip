package duke.task;

import duke.DukeException;

/**
 * Outlines a general task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected char category;
    protected String name;

    /**
     * Initialises properties of a Task.
     *
     * @param description Description about the task.
     * @param category Type of task.
     */
    public Task(String description, char category) {
        this.description = description;
        this.isDone = false;
        this.category = category;
        this.name = "";
    }

    /**
     * Returns X if task is done and a whitespace if task is not yet done.
     *
     * @return Whether task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns information about the task.
     *
     * @return Description of task.
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Retrieves type of the task.
     *
     * @return Category of task.
     */
    public char getCat() {
        return this.category;
    }

    /**
     * Retrieves end timing of task.
     *
     * @return Due time of task.
     */
    public String getDueTime() {
        return "";
    }

    /**
     * Returns task name.
     *
     * @return Name of task.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Converts Task to string format.
     *
     * @return Task as a string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
