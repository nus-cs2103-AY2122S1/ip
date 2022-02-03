package duke.task;

import duke.DukeException;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representing whether the task is done.
     * Used in the String representation of the task.
     *
     * @return String representing whether the task is done.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]"; // Done task marked with X
    }

    /**
     * Returns a String representing whether the task in done.
     * Used in the representation of the task in the storage file.
     *
     * @return String representing whether the task in done in the storage.
     */
    public String getStatusNumber() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Changes the description of the task.
     *
     * @param newDesc New description to give to the task.
     */
    public void changeDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.getDescription());
    }

    /**
     * Abstract method to return the String representation of the task as used in the storage.
     *
     * @return String representation of the task in the storage format.
     */
    public abstract String toStorageString();

    public String toUndoneString() {
        return "[ ] " + this.getDescription();
    };
}
