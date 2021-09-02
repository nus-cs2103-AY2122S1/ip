package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that handles events.
 */
public class Event extends Task {

    protected static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
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

    /**
     * Checks whether time is the same date.
     *
     * @param date Date to check for.
     * @return Whether date and time are the same dates.
     */
    public boolean isSameTime(LocalDate date) {
        return time.equals(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DATE_FORMATTER) + ")";
    }

    @Override
    public String convertToFileFormat() {
        return "E / " + super.convertToFileFormat() + " / " + time;
    }
}
