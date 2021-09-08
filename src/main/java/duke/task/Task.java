package duke.task;

import java.util.Objects;

import duke.Parser;

/**
 * This class represents a task.
 */
public class Task {
    /** Description of the task. */
    private String description;
    /** State of the task. */
    private boolean isDone;

    /**
     * Constructs a task instance using the given description.
     *
     * @param description The given description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task using the given description and complete state.
     *
     * @param description The given description.
     * @param isDone      The given complete state.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns X when the task is done, space otherwise.
     *
     * @return X when the task is done, space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task following .txt format.
     *
     * @return A string representation of the task following .txt format.
     */
    public String toTxtFormat() {
        String state = isDone ? "1" : "0";
        return state + Parser.SPLITER + description;
    }

    /**
     * Returns true if the given object is equal to this, otherwise false.
     *
     * @param o The given object.
     * @return True if the given object is equal to this, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && Objects.equals(description, task.description);
    }
}
