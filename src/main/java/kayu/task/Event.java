package kayu.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a {@link kayu.task.Task} that has a {@link #KEYWORD} for its DateTime field.
 */
public class Event extends Task {

    /** Keyword for Event {@link kayu.task.Task} class. */
    public static final String KEYWORD = "E";

    /** Split word used for command parsing. */
    public static final String SPLIT_WORD = "at";

    private final LocalDate atDate;
    private final LocalTime atTime;

    /**
     * Initializes the Event {@link kayu.task.Task}.
     *
     * @param description String description of Task.
     * @param isDone Boolean true if complete, else false.
     * @param atDate {@link LocalDate} of Event instance.
     * @param atTime {@link LocalTime} of Event instance.
     */
    public Event(String description, boolean isDone, LocalDate atDate, LocalTime atTime) {
        super(description, isDone);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Initializes the Event {@link kayu.task.Task}. Overloaded constructor that sets
     * <code>isDone</code> field to false.
     *
     * @param description String description of Task.
     * @param atDate {@link LocalDate} of Event instance.
     * @param atTime {@link LocalTime} of Event instance.
     */
    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Returns the {@link LocalDate} of Event instance.
     *
     * @return {@link LocalDate} of Event instance.
     */
    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Returns the {@link LocalTime} of Event instance.
     *
     * @return {@link LocalTime} of Event instance.
     */
    public LocalTime getAtTime() {
        return atTime;
    }

    /**
     * Provides a formatted String of the {@link #KEYWORD} field.
     *
     * @return Formatted String for field {@link #KEYWORD}.
     */
    private String getFormattedAt() {
        return "("
                + SPLIT_WORD
                + ": "
                + atDate
                + " " + atTime
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
                + atDate
                + SPLIT_TEMPLATE
                + atTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return '[' + KEYWORD + ']' + super.toString() + "\n   " + getFormattedAt();
    }
}
