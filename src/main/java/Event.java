import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A task with a start and end time
 */
public class Event extends Task {
    /** A string representing the start and end time of the event **/
    private LocalDate eventDate;

    /**
     * Initializes a new Event
     * @param name Name of task
     * @param startTime Start time of task
     */
    public Event(String name, LocalDate startTime) {
        super(name);
        this.eventDate = startTime;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        String prettyDate = eventDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return String.format("[E][%s] %s (at: %s)", isDone, getName(), prettyDate);
    }
}
