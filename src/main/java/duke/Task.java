package duke;

/**
 * A class that encapsulates a Task to be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to check if a small string of characters is contained in this task.
     */
    public boolean checkDescription(String input) {
        return description.contains(input);
    }

    /**
     * Get Status of this task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark this task as done.
     */
    public void markAsDone() {
        isDone = true; // mark this task as done
    }

    /**
     * Print the information of this task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
