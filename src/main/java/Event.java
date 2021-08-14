/**
 * This class encapsulates an Event.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    private final String dateTime;

    /**
     * Instantiates a new Event.
     *
     * @param description the description of the event.
     * @param dateTime the datetime of event.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * String representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime);
    }
}
