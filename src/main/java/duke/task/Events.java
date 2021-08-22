package duke.task;

/**
 * Encapsulates a duke.task to be done at a specified time.
 */
public class Events extends Task {
    /** When the duke.task begins */
    private String limit;

    /**
     * Constructor of an duke.task.Events object.
     *
     * @param description Description of the duke.task.
     * @param limit When the duke.task begins.
     */
    public Events(String description, String limit) {
        super(description);
        this.limit = limit;
    }

    @Override
    public String saveData() {
        return "event " + super.saveData() + " /at " + this.limit;
    }

    /**
     * Returns the string form of the duke.task.Events object.
     *
     * @return The string form of the duke.task.Events.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.limit + ")";
    }
}
