package duke;

/**
 * Represents a ToDo Task which inherits from Task.
 *
 * @author Sherman Ng Wei Sheng
 */
public class ToDo extends Task {
    
    /**
     * Constructor to initialize a new ToDo.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor to initialize a new ToDo.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    
    /**
     * Returns the string representation of the task in a desired format.
     *
     * @return The String representation of the task, prefixed with a status icon and todos identifier.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String encodeTaskForStorage() {
        int encodedIsDone = this.getIsDone() ? 1 : 0;
        return String.format("T | %d | %s", encodedIsDone, this.getDescription());
    }
}
