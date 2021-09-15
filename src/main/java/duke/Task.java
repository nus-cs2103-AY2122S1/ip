package duke;

import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task Object.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task to done.
     *
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the character representing the task type.
     *
     * @return The character representing the task type.
     */
    public abstract char getTaskType();

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the time of the task.
     *
     * @return Time of the task.
     */
    public abstract String getTime();

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Checks if two tasks are equal based on their description and completion status.
     *
     * @param o
     * @return A boolean indicating whether the object being compared to is equal to the task.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(description, task.description);
    }
}
