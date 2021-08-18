/**
 * Encapsulates a task to be done at a specified time.
 */
public class Events extends Task {
    /** When the task begins */
    private String limit;

    /**
     * Constructor of an Events object.
     *
     * @param description Description of the task.
     * @param limit When the task begins.
     */
    public Events(String description, String limit) {
        super(description);
        this.limit = limit;
    }

    /**
     * Returns the string form of the Events object.
     *
     * @return The string form of the Events.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.limit + ")";
    }
}
