package duke;

/**
 * Represents a todo task
 */
public class ToDo extends Task {
    private String description;
    private final String TASK_ICON = "T";

    /**
     * Constructor for the ToDo task
     * @param description
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Returns the task icon that represents the todo task
     *
     * @return The task icon that represents the todo task
     */
    public String getTaskIcon() {
        return this.TASK_ICON;
    }

    /**
     * Overridden equals method to check for task equality based on description
     *
     * @param o representing any generic object
     * @return True if the object is equal to the deadline task based on description
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ToDo)) {
            return false;
        }
        ToDo e = (ToDo) o;
        return this.description.equals(e.description);
    }

    /**
     * Overridden toString method to output the task in a readable format
     * @return String representing task in a readable format
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description;
    }
}
