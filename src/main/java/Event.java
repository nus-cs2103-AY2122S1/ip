import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that stores the day and time of the event.
 */
public class Event extends Task {
    /**
     * The date of the event.
     */
    protected LocalDate date;

    /**
     * The time of the event.
     */
    protected LocalTime time;

    /**
     * A constructor used to initialize the event.
     *
     * @param description the description of the event.
     * @param date the date of the event.
     * @param time the time of the event.
     */
    protected Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Return the string representation of event.
     *
     * @return the string representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("d/MM/yyyy")) + ")";
    }
}
