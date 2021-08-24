package kayu.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class.
 *
 * This class is a Task that has a 'by' datetime String.
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
     * Provides a formatted String of the 'by' field.
     *
     * @return formatted String for field 'by'
     */
    private String getFormattedBy() {
        return "("
                + SPLIT_WORD
                + ": "
                + byDate
                + " " + byTime
                + ")";
    }
    
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
