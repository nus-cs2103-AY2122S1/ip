package daisy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that handles deadlines.
 */
public class Deadline extends Task {

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    static final String DEADLINE_STRING_FORMAT = "[D]%s (by: %s)%s";
    static final String DEADLINE_FILE_FORMAT = "D // %s // %s // %s";
    protected LocalDate by;

    /**
     * Constructs the Deadline object.
     *
     * @param description Description of deadline task.
     * @param by Date of deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks whether by is the same date.
     *
     * @param date Date to check for.
     * @return Whether date and by are the same dates.
     */
    public boolean isSameBy(LocalDate date) {
        assert date != null : "Date to check is null";
        return by.isEqual(date);
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_STRING_FORMAT, super.toString(), by.format(DATE_FORMATTER), getTagsString());
    }

    @Override
    public String convertToFileFormat() {
        return String.format(DEADLINE_FILE_FORMAT, super.convertToFileFormat(), by, getTagsFileFormat());
    }
}
