/**
 * Represents a ToDo Task which inherits from Task.
 *
 * @author Sherman Ng Wei Sheng
 */
public class ToDo extends Task {
    
    /**
     * Constructor to initialise a new ToDo.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overridden toString method.
     *
     * @return The String representation of the task, prefixed with a status icon and todos identifier.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}
