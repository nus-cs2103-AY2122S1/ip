package duke.task;

/**
 * Encapsulates a task to be done at a specified time.
 */
public class Events extends Task {
    /** When the task begins */
    private final String LIMIT;

    /**
     * Constructs a Events object.
     *
     * @param description Description of the task.
     * @param limit When the task begins.
     */
    public Events(String description, String limit) {
        super(description);
        this.LIMIT = limit;
    }

    /**
     * Returns the Events object as data for saving.
     *
     * @return Events object save data.
     */
    @Override
    public String saveData() {
        return "event " + super.saveData() + " /at " + this.LIMIT;
    }

    /**
     * Returns the string form of the Events object.
     *
     * @return The string form of the Events.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.LIMIT + ")";
    }
}
