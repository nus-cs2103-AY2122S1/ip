package kayu.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class.
 *
 * This class is a {@link kayu.task.Task} that has a {@link #KEYWORD} 
 * for its DateTime field.
 */
public class Deadline extends Task {

    public final static String KEYWORD = "D";

    public final static String SPLIT_WORD = "by";

    private final LocalDate byDate;

    private final LocalTime byTime;

    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }


    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public LocalTime getByTime() {
        return byTime;
    }

    /**
     * Provides a formatted String of the {@link #KEYWORD} field.
     *
     * @return Formatted String for field {@link #KEYWORD}.
     */
    private String getFormattedBy() {
        return "("
                + SPLIT_WORD
                + ": "
                + byDate
                + " " + byTime
                + ")";
    }

    /**
     * See {@link kayu.task.Task#toEncodedString}.
     */
    @Override
    public String toEncodedString() {
        return KEYWORD 
                + SPLIT_TEMPLATE
                + super.toEncodedString() 
                + SPLIT_TEMPLATE
                + byDate
                + SPLIT_TEMPLATE 
                + byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + ' ' + getFormattedBy();
    }
}
