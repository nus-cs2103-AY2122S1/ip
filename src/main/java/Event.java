/**
 * Class that encapsulates an Event task.
 */
public class Event extends Task {
    /**
     * String that indicates the time of the event.
     */
    protected String timeOfEvent;

    /**
     * Public constructor to create an Event task
     *
     * @param description Description of the event.
     * @param timeOfEvent Time of the event.
     */
    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * String representation of an event task.
     *
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeOfEvent +")";
    }

}
