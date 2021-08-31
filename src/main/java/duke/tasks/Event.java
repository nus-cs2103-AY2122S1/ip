package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an Event Task.
 */
public class Event extends Task {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-uuuu H:mm");
    private static DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm a");
    protected LocalDateTime date;

    /**
     * Constructs a Deadline Task that is not done.
     *
     * @param description Description of event task.
     * @param at Date that event is occurring.
     */
    public Event(String description, String at) {
        super(description, false);
        this.date = parseDateTime(at);
    }

    /**
     * 
     * @param description Description of event task.
     * @param at Date that event is occurring.
     * @param isDone Whether task is done.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.date = parseDateTime(at);
    }
    
    private LocalDateTime parseDateTime(String at) {
        // TODO: need to catch exception
        return LocalDateTime.parse(at, FORMATTER);
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + date.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DISPLAY_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return super.equals(obj) && date.equals(other.date);
        }
        return false;
    }
}
