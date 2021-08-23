import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Time/place of an event */
    protected String at;
    protected LocalDateTime dateTime;

    /**
     * Constructor for Event. Takes in a String description and at.
     * Initialises description and time of event.
     *
     * @param description The name of the event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(at, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }
}