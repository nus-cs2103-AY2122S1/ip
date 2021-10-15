package duke;

/**
 * Task with an Event.
 */
public class Event extends Task { //Starts and ends by a certain time

    protected String by; //Range of timing

    /**
     * Instantiates an Event class.
     *
     * @param description
     * @param by
     */
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + by + ")";
    }
}
