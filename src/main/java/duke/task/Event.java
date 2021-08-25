package duke.task;

import duke.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a description and a start and end date/time
 */
public class Event extends Task {

    private LocalDate eventDate;

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
