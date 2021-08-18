/**
 * The Event class encapsulates all the details of each event.
 */
public class Event extends Task {
    public Event(String message) {
        super(message);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
