/**
 * Represents a Task which contains a String description and a boolean status.
 *
 * @author Sherman Ng Wei Sheng
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor to initialize a new Task.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to initialize a new Task with a boolean argument.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the particular task.
     * 
     * @return The status icon depending on whether the task is marked as done.
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
     * Returns the string representation of the task in a desired format.
     * 
     * @return The String representation of the task, prefixed with a status icon.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean getIsDone() {
        return this.isDone;
    }
    
    public abstract String encodeTaskForStorage();
}
