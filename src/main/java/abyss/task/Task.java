package abyss.task;

import java.time.LocalDate;

import abyss.exception.InvalidCommandException;

/**
 * Represents an general task with a description and whether it is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getIsDone() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void setDate(LocalDate date) throws InvalidCommandException;

    /**
     * Returns formatted details of the task.
     *
     * @return Formatted task details.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns details of the task formatted for file entry.
     *
     * @return Formatted task details.
     */
    public abstract String toFileEntry();
}
