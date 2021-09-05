package bobcat.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobcat.exception.LogicException;

/**
 * A Task which represents a task with a starting time
 */
public class Event extends Task {
    protected LocalDate startTime;

    /**
     * Returns an Event object which represents an event in the TaskList
     * @param entry description of Event
     * @param isComplete completion status of Event
     * @param startTime when the Event starts
     */
    public Event(String entry, boolean isComplete, String startTime) {
        super(entry, isComplete);
        try {
            this.startTime = LocalDate.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new LogicException("Cannot understand given date. Is it in \"yyyy-mm-dd\" format?");
        }
    }

    public Event(String entry, String startTime) {
        this(entry, false, startTime);
    }

    private String formatDateTime(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDateTime(startTime) + ")";
    }
}
