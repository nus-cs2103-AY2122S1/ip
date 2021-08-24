// class that handles Event tasks 
// -> Event is a type of Task happening at a date/time
public class Event extends Task {
    private String eventTime;

    // Constructor for an Event
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    // Constructor for an Event that may be completed
    public Event(String description, String eventTime, Boolean isComplete) {
        super(description, isComplete);
        this.eventTime = eventTime;
    }

    @Override
    public String getFileRepr() {
        return "E" + super.getFileRepr() + " | " + this.eventTime;
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
