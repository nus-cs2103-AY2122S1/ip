package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A task with a start and end time
 */
public class Event extends Task {
    /** Date of the event **/
    private LocalDate eventDate;

    /**
     * Initializes a new Event
     *
     * @param name Name of task
     * @param eventDate Start time of task
     */
    public Event(String name, LocalDate eventDate) {
        super(name);
        this.eventDate = eventDate;
    }

    /**
     * Initializes a new Event with done property
     *
     * @param name Name of event
     * @param eventDate Date of event
     * @param isDone Whether event is done
     */
    public Event(String name, LocalDate eventDate, boolean isDone) {
        super(name, isDone);
        this.eventDate = eventDate;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "✔" : " ";
        String prettyDate = eventDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return String.format("[E][%s] %s (at: %s)", isDone, getName(), prettyDate);
    }
}
