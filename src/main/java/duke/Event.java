package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A task that happens at a specified time.
 *
 * @author Gabriel Goh
 */
public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Empty constructor for comparing event objects by description.
     * To find duplicate tasks.
     *
     * @param description Event description
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructor when date and time of event are given.
     *
     * @param description Event description
     * @param at          When event occurs
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        date = at.toLocalDate();
        time = at.toLocalTime();
    }

    /**
     * Constructor when only date of event is given.
     *
     * @param description Event description
     * @param at          When event occurs
     */
    public Event(String description, LocalDate at) {
        super(description);
        date = at;
        time = null;
    }

    /**
     * Convert event into savable format.
     *
     * @return String to save
     */
    public String saveString() {
        return "E | " + super.saveString() + " | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " " + (time == null ? "All day"
                : time.format(DateTimeFormatter.ofPattern("h.mma", Locale.ENGLISH)));
    }

    /**
     * String representation of event for printing
     *
     * @return String to print
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " " + (time == null ? "All day"
                : time.format(DateTimeFormatter.ofPattern("h.mma", Locale.ENGLISH))) + ")";
    }
}
