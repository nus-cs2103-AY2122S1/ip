/**
 * This class creates event instances which are to be done at a certain time.
 */
public class Event extends Task {
    // The time of the event.
    protected String time;

    /***
     * Constructor to create a event.
     *
     * @param name The name of the event.
     * @param time The time of the event.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    /***
     * Returns the string representation of the event.
     *
     * @return The name of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}