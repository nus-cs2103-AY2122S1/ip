/**
 * A type of Task. Inherits from Task, takes in a date/time that
 * specifies when event is happening.
 */
public class Event extends Task{
    private String dateTime;

    /**
     * Constructor for Event. Takes in a description and a dateTime.
     *
     * @param description The description of the event.
     * @param dateTime The date/time the event occurs.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the Event class.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + this.dateTime + ")";
    }
}
