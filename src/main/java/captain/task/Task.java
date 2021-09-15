package captain.task;

import captain.DukeException.MissingDescriptionException;

/**
 * Encapsulates the details of a Task object.
 *
 * @author Adam Ho
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with the specified description.
     * @param description The task description.
     */
    public Task(String description) throws MissingDescriptionException {
        if (description == null) {
            throw new MissingDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    public boolean checkStatus() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     * @return A String representation of the task status.
     */
    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Represents task object as a string.
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.description;
    }
}
