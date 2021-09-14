package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    String label;

    /**
     * Initializes Task object.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task with an X if completed
     * @return the status of the task as "X" or empty
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Change the boolean value of isDone to true if completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
