/**
 * A type of Task. Inherits from Task, takes in a date/time that
 * specifies when event is happening.
 */
public class Event extends Task{
    private String dateTime;

    /**
     * Constructor for Event. Takes in a description and a dateTime.
     *
     * @param description The description of the event
     * @param dateTime The date/time the event occurs
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor for Event.
     * Takes in description, dateTime and the status of the event.
     *
     * @param description The description of the event
     * @param dateTime The date/time the event occurs
     * @param isDone indicates if the event is done
     */
    public Event(String description, String dateTime, boolean isDone) {
        super(description);
        this.dateTime = dateTime;
        super.setDone(isDone);
    }

    /**
     * Returns the string representation of the Event class.
     *
     * @return String representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + this.dateTime + ")";
    }

    /**
     * Returns a string formatted for writing into file.
     *
     * @return String representation of the task for file writing
     */
    @Override
    public String saveString() {
        return "E," + super.saveString() + "," + this.dateTime;
    }
}
