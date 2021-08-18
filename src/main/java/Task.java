/**
 * This class encapsulates a Task object.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description, isDone false by default.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter method for status using isDone
     *
     * @return an icon 'X' if complete, space if otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter method for isDone of a Task object
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
