package duke;

/**
 * Represents a deadline task
 */
public class Deadlines extends Task {
    private String description;
    private String time;
    private final String TASK_ICON = "D";

    /**
     * Constructor for the deadline task
     *
     * @param description
     * @param time
     */
    public Deadlines(String description, String time) {
        super(description);
        this.description = description;
        this.time = time;
    }

    /**
     * Obtains the correct task icon for a deadline task
     *
     * @return Task icon
     */
    public String getTaskIcon() {
        return this.TASK_ICON;
    }

    /**
     * Overridden equals method to check for task equality based on description and time
     *
     * @param o representing any generic object
     * @return True if the object is equal to the deadline task based on description and time
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadlines)) {
            return false;
        }
        Deadlines e = (Deadlines) o;
        return this.description.equals(e.description) && this.time.equals(e.time);
    }

    /**
     * Overridden toString method to output the task in a readable format
     *
     * @return String representing task in a readable format
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description + "//" + time;
    }
}
