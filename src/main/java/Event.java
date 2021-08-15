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
     * Return the string representation of event.
     *
     * @return the string representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dayTime + ")";
    }
}
