package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Storage;

/**
 * A task with a description and a start and end date/time
 */
public class Event extends Task {

    private LocalDate eventDate;

    /**
     * Constructs an Event object given its description, completion status
     * and event date
     * @param description Description of the event
     * @param completed Completition status of the event
     * @param eventDate Date of the event
     */
    public Event(String description, boolean completed, LocalDate eventDate) {
        super(description, completed);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String encode() {
        return "e" + Storage.DELIMITER
                + (this.isCompleted() ? "1" : "0") + Storage.DELIMITER
                + eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + Storage.DELIMITER
                + this.getDescription();
    }
}
