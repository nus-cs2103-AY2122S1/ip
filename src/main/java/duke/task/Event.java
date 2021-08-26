package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that handles events.
 */
public class Event extends Task {

    protected static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate time;

    /**
     * Constructs the Event object.
     *
     * @param description Description of event task.
     * @param time Date of event task.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E / " + super.toFileFormat() + " / " + time;
    }
}
