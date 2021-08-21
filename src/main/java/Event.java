import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    
    
    /**
     * Constructor for event.
     *
     * @param description description of the event
     * @param date date of the event
     * @param startTime start time of the event
     * @param endTime end time of the event
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                startTime.format(timeFormatter) + " - " + endTime.format(timeFormatter) + ")";
    }
}
