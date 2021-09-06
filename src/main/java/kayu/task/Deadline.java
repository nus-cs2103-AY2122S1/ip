package kayu.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a {@link kayu.task.Task} that has a {@link #KEYWORD} for its DateTime field.
 */
public class Deadline extends Task {

    /** Keyword for Deadline {@link kayu.task.Task} class. */
    public static final String KEYWORD = "D";

    /** Split word used for command parsing. */
    public static final String SPLIT_WORD = "by";

    private final LocalDate byDate;
    private final LocalTime byTime;

    /**
     * Initializes the Deadline {@link kayu.task.Task}.
     *
     * @param description String description of Task.
     * @param isDone Boolean true if complete, else false.
     * @param byDate {@link LocalDate} of Deadline instance.
     * @param byTime {@link LocalTime} of Deadline instance.
     */
    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Initializes the Deadline {@link kayu.task.Task}. Overloaded constructor that sets
     * <code>isDone</code> field to false.
     *
     * @param description String description of Task.
     * @param byDate {@link LocalDate} of Deadline instance.
     * @param byTime {@link LocalTime} of Deadline instance.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Returns the {@link LocalDate} of Deadline instance.
     *
     * @return {@link LocalDate} of Deadline instance.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns the {@link LocalTime} of Deadline instance.
     *
     * @return {@link LocalTime} of Deadline instance.
     */
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
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return '[' + KEYWORD + ']' + super.toString() + "\n   " + getFormattedBy();
    }
}
