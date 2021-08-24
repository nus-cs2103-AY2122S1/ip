import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task. Inherits from Task, takes in a date/time that
 * specifies when event is happening.
 */
public class Event extends Task{
    private LocalDate dateTime;

    /**
     * Constructor for Event. Takes in a description and a dateTime.
     *
     * @param description The description of the event.
     * @param dateTime The date/time the event occurs.
     */
    public Event(String description, LocalDate dateTime) {
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
        return "[E]" + super.toString() + " (on: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
