public class ToDos extends Task {
    
    /**
     * Constructor to initialise a new Task.
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Overridden toString method.
     *
     * @return The String representation of the task, prefixed with a status icon and todos identifier.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + this.description;
    }
}
