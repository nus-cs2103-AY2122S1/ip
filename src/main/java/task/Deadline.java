package task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class.
 *
 * This class is a Task that has a 'by' datetime String.
 */
public class Deadline extends Task {

    public final static String SPLIT_WORD = "by";

    private final LocalDate byDate;

    private final LocalTime byTime;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Provides a formatted String of the 'by' field.
     *
     * @return formatted String for field 'by'
     */
    public String getFormattedBy() {
        return "("
                + SPLIT_WORD
                + ": "
                + byDate
                + ((byTime == null) ? "": " " + byTime)
                + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + ' ' + getFormattedBy();
    }
}
