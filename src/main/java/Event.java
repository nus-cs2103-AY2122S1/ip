/**
 * Event class, subclass of Task.
 * Encapsulates a Task with a start/end time
 */
public class Event extends Task {

    //The start time of the Event
    protected String time;

    /**
     * The constructor for the Event class
     * @param description The description of the event
     * @param startTime The start time of the event
     */
    public Event(String description, String startTime, boolean isDone) {
        super(description.trim(), isDone);
        this.time = startTime.trim();
    }

    @Override
    public String strForSaving() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.time;
    }

    /**
     * Overridden toString method for the Event class
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.firstLetter(time) + ")";
    }
}
