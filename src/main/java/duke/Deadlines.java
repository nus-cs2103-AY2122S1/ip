package duke;

/**
 * Represents a deadline task
 */
public class Deadlines extends Task {
    protected String time;

    /**
     * Constructor for the deadline task
     *
     * @param description
     * @param time
     */
    public Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Method to obtain the correct task icon
     *
     * @return Task icon
     */
    public String getTaskIcon() {
        return "D";
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
     * @return String representing task in a readable format
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description + "//" + time;
    }
}
