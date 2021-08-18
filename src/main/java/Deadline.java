public class Deadlines extends Task {
    private String dateTime;
    
    /**
     * Constructor to initialise a new Task.
     *
     * @param description The description of the task.
     */
    public Deadlines(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Overridden toString method.
     *
     * @return The String representation of the task, prefixed with a status icon and deadlines identifier.
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + this.description;
    }
}
