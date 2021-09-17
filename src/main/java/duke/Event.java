package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing an event task.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private String at;
    private LocalDate atDate;

    /**
     * A constructor for the event task.
     *
     * @param description The description of the task.
     * @param at The note represented by a string.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.atDate = null;
    }

    /**
     * A constructor for the event task.
     *
     * @param description The description of the task.
     * @param atDate The date of the event task.
     */
    public Event(String description, LocalDate atDate) {
        super(description);
        this.at = null;
        this.atDate = atDate;
    }

    /**
     * A constructor for the deadline task which is mainly used for restoring from duke.txt.
     *
     * @param isDone The status of the task.
     * @param description The description of the task.
     * @param at The note represented by a string.
     * @param atDate The date of the event task.
     */
    public Event(boolean isDone, String description, String at, LocalDate atDate) {
        super(isDone, description);
        this.at = at;
        this.atDate = atDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                atDate != null ? atDate.format(DATE_TIME_FORMAT) : at
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toBackupFormat() {
        return String.format(
                "E | %s | %s | %s | ",
                super.toBackupFormat(),
                at == null ? "" : at,
                atDate == null ? "" : atDate.format(DATE_TIME_FORMAT)
        );
    }

    /**
     * Returns true if the tasks clashed.
     *
     * @return true if the tasks clashed.
     */
    public boolean clash(Task t) {
        if (t instanceof Event) {
            Event temp = (Event) t;
            if (at != null) {
                return at.equals(temp.at);
            } else {
                return atDate.equals(temp.atDate);
            }
        }
        return false;
    }
}
