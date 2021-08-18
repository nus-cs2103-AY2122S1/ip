/**
 * Event encapsulates the name of the event as well as the
 * when the event is occurring.
 */

public class Event extends Task{
    private String at;

    /**
     * Construct Event object with a given name, and time of the event.
     * @param name The name of the event
     * @param at Time at which event is held
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Converts the event object into a string.
     * @return A string containing the type (event), completion status and time
     * at which event is held.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
