import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event object.
 * Event objects are task objects that start and end at specific times.
 *
 * @author Dickson
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets string representation of event task.
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString() + " (at: " +
                date.format(dateFormatter) + " " +
                startTime.format(timeFormatter) + "-" +
                endTime.format(timeFormatter) + ")";
    }
}
