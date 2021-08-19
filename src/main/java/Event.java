/**
 * This class encapsulates an Event
 * (a task that happens at a specific time)
 * e.g. team project meeting at 2pm.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    private String time;

    /**
     * Instantiates a new Event.
     *
     * @param name the title of the event.
     * @param time the time of the event.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    /**
     * String representation of the event.
     *
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
