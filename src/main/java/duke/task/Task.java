package duke.task;

import duke.exception.DukeException;

/**
 * The Task class encapsulates a task for the user to complete.
 */
public class Task {
    /** The description of the task. */
    private String description;

    /** A boolean indicating whether the task has been completed. */
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     * @param isDone A boolean indicating whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the given task as done.
     */
    public void markAsDone() {
        if (this.isDone) {
            throw new DukeException("The task indicated has already been marked as done.");
        }
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getSaveFormat() {
        return (isDone ? "1" : "0") + "|" + this.description;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task otherTask = (Task) other;
            return this.description.equals(otherTask.description) && this.isDone == otherTask.isDone;
        } else {
            return false;
        }
    }
}
