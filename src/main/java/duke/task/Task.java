package duke.task;

/**
 * Represents a general Task that can be added to our task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    /**
     * Constructs a task.
     *
     * @param description The decription of the task.
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Returns the status of a task.
     *
     * @return "X" if task is done else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.getStatusIcon(), this.priority, this.description);
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts a Task into the format for storing.
     *
     * @return A String that follows the storing conventions for our data file.
     */
    public String convertToFileFormat() {
        return String.format(" | %s | %s | %s", this.isDone ? "1" : "0", this.priority, this.description);
    }
}
