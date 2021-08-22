/**
 * A task that stores the day and time of the event.
 */
public class Event extends Task {
    /**
     * The day and time of the event.
     */
    protected String dayTime;

    /**
     * A constructor used to initialize the event.
     *
     * @param description the description of the event.
     * @param dayTime the day and time of the event.
     */
    protected Event(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    /**
     * A constructor used to initialize the event through file input.
     *
     * @param description the description of the event.
     * @param isCompleted the state of the event.
     * @param dayTime the day and time of the event.
     */
    protected Event(String description, boolean isCompleted, String dayTime) {
        super(description, isCompleted);
        this.dayTime = dayTime;
    }

    /**
     * Return the string representation of event.
     *
     * @return the string representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dayTime + ")";
    }

    /**
     * Return the string representation of event for file input/output.
     *
     * @return the string representation of event for file input/output.
     */
    @Override
    public String fileFormat() {
        String displayCompletion = this.isCompleted ? "1" : "0";
        return String.format("%s | %s | %s | %s", "T", displayCompletion, this.description, this.dayTime);
    }
}
