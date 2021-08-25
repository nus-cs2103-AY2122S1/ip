package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// class that handles Event tasks 
// -> Event is a type of Task happening at a date/time
public class Event extends Task {

    private LocalDateTime eventTime;

    // Constructor for an Event
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    // Constructor for an Event that may be completed
    public Event(String description, LocalDateTime eventTime, Boolean isComplete) {
        super(description, isComplete);
        this.eventTime = eventTime;
    }

    @Override
    public String getFileRepr() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "E" + super.getFileRepr() + " | " + this.eventTime.format(format);
    }

    // String representation of an Event
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        
        return "[E]["
            + ((this.isCompleted()) ? "X] " : " ] ")
            + this.getDescription()
            + " (at: " 
            + this.eventTime.format(format)
            + ")";
    }
}
