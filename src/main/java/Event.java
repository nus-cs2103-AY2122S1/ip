/**
 * The Event class encapsulates all the details of each event.
 */
public class Event extends Task {
    private final String timePeriod;

    public Event(String message, String timePeriod){
        super(message);
        this.timePeriod = timePeriod;
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timePeriod + ")";
    }
}
