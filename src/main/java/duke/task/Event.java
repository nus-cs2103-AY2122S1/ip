package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
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
     * Constructor of Event instance.
     *
     * @param description Event description
     * @param at          When event occurs
     */
    public Event(String description, Temporal at) {
        super(description);
        if (at instanceof LocalDateTime) {
            LocalDateTime a = (LocalDateTime) at;
            date = a.toLocalDate();
            time = a.toLocalTime();
        } else if (at instanceof LocalDate) {
            date = (LocalDate) at;
            time = null;
        }

    }


    /**
     * Converts event into savable format.
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
     * Returns string representation of event for printing
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
