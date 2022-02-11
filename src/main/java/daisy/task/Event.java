package daisy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that handles events.
 */
public class Event extends Task {

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    static final String EVENT_STRING_FORMAT = "[E]%s (at: %s)%s";
    static final String EVENT_FILE_FORMAT = "E // %s // %s // %s";
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
        assert date != null : "Date to check is null";
        return time.equals(date);
    }

    @Override
    public String toString() {
        return String.format(EVENT_STRING_FORMAT, super.toString(), time.format(DATE_FORMATTER), getTagsString());
    }

    @Override
    public String convertToFileFormat() {
        return String.format(EVENT_FILE_FORMAT, super.convertToFileFormat(), time, getTagsFileFormat());
    }
}
