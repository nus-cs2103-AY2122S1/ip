package nyx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * This class inherits from the parent abstract Task class.
 */
public class Deadline extends Task {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd H:m";
    private final LocalDateTime by;

    /**
     * Constructs a deadline task with its description, deadline and an indicator of whether it is marked as done.
     * @param content Description of the deadline task.
     * @param by Datetime of the deadline.
     * @param isDone Indicator to indicate whether the deadline task is done.
     */
    public Deadline(String content, String by, boolean isDone) {
        super(content, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    /**
     * Constructs an uncompleted deadline task with its description and deadline.
     * @param content Description of the deadline task.
     * @param by Datetime of the deadline.
     */
    public Deadline(String content, String by) {
        this(content, by, false);
    }

    /**
     * Returns a String representation of the deadline task in the format required for saving into hard disk.
     * @return String representation of the deadline task in the format required to save into hard disk.
     */
    @Override
    public String dataFormat() {
        String dateFormat =  by.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        return String.format("D, %d, %s, %s\n", getStatusInt(), getContent(), dateFormat);
    }

    /**
     * Returns the String representation of the deadline task.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, K:ma"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }
}