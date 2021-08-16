// class that handles Event tasks 
// -> Event is a type of Task happening at a date/time
public class Event extends Task {
    private String eventTime;

    // Constructor for an Event
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    // String representation of an Event
    @Override
    public String toString() {
        return "[E]["
            + ((this.isCompleted()) ? "X] " : " ] ")
            + this.getDescription()
            + " (at: " 
            + this.eventTime
            + ")";
    }
}
