package duke;

/**
 * duke.Event class to create Events for the duke list.
 */

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns the time of the event stored in the list.
     *
     * @return time of the event
     */
    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), time);
    }
}
