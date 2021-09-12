package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;

/**
 * A task that has to be done by a specified time.
 *
 * @author Gabriel Goh
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Empty constructor for comparing deadline objects by description.
     * To find duplicate tasks.
     *
     * @param description Deadline description
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Constructor of Deadline instance.
     *
     * @param description Deadline description
     * @param by          When it is due
     */
    public Deadline(String description, Temporal by) {
        super(description);
        if (by instanceof LocalDateTime) {
            LocalDateTime b = (LocalDateTime) by;
            date = b.toLocalDate();
            time = b.toLocalTime();
        } else if (by instanceof LocalDate) {
            date = (LocalDate) by;
            time = null;
        }
    }

    /**
     * Converts deadline into savable format.
     *
     * @return String to save
     */
    @Override
    public String saveString() {
        return "D | " + super.saveString() + " | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " " + (time == null ? "All day"
                : time.format(DateTimeFormatter.ofPattern("h.mma", Locale.ENGLISH)));
    }

    /**
     * Returns string representation of deadline for printing.
     *
     * @return String to print
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " " + (time == null ? "All day"
                : time.format(DateTimeFormatter.ofPattern("h.mma", Locale.ENGLISH))) + ")";
    }
}
