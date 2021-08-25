package duke.task;

import duke.exception.DukeException;

/**
 * The Task class encapsulates a task for the user to complete.
 * It contains information about the task's description and its completion status.
 */
public class Task {
    /** The description of the task. */
    private String description;

    /** A boolean indicating whether the task has been completed. */
    private boolean isDone;

    /**
     * Constructs a task object that is not completed yet.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task object with a specifiable completion status.
     *
     * @param description The description of the task.
     * @param isDone A boolean indicating whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the icon representing the completion status of the task.
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

    /**
     * Returns the format in which the task is stored in the save file.
     *
     * @return A string representing how the task is saved.
     */
    public String getSaveFormat() {
        return (isDone ? "1" : "0") + "|" + this.description;
    }

    /**
     * Checks whether another object is equal with this task.
     *
     * @param other The object being compared to.
     * @return true if both are tasks and share the same description and completion status, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task otherTask = (Task) other;
            return this.description.equals(otherTask.description) && this.isDone == otherTask.isDone;
        } else {
            return false;
        }
    }

    /**
     * Checks if the task description contains a specified keyword.
     *
     * @param keyword The keyword being searched.
     * @return true if the task description contains the specified keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
