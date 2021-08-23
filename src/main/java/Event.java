public class Event extends TaskItem {

    protected String date;
    protected String time;
    protected boolean readFromFile = false;

    /**
     * Constructor for creating an Event object.
     * @param description description of the event.
     * @param date date of the event.
     * @param time the time that this event is going to occur.
     */
    public Event(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
        this.readFromFile = false;
    }

    public Event(String description) {
        super(description);
        this.readFromFile = true;
    }

    /**
     * Overriden toString() method.
     * @return a String representation of an Event object.
     */
    @Override
    public String toString() {
        if (!readFromFile) {
            return "[E]" + super.toString() + "(at: " + this.date + " " + this.time + ")";
        } else {
            return "[E]" + super.toString();
        }
    }
}
