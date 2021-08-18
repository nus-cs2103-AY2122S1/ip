public class Event extends Task{

    private String date;
    private String time;

    /**
     * Constructor for event class
     * @param name Name for the event
     * @param date date the event is held
     * @param time time the event is held
     */
    public Event(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * String representation of the event class
     * @return String. See above.
     */
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s %s)", this.date, this.time);
    }
}
