package cs2103.duke;

/**
 * This class encapsulates a Task object.
 */

public class Task {
    protected int index;
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description, isDone false by default.
     *
     * @param index       Index of the task, starting from 0.
     * @param description Description of the task.

     */
    public Task(int index, String description) {
        this.index = index;
        this.description = description;
        this.isDone = false;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Getter method for status using isDone.
     *
     * @return A letter 'X' if task is complete, an empty space if otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter method for isDone of a Task object.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of Task.
     *
     * @return String representation of Task.
     */
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
