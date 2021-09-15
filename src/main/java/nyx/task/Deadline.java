package nyx.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nyx.NyxException;

/**
 * Represents a task with a deadline.
 * This class inherits from the parent abstract Task class.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a deadline task with its description, deadline and an indicator of whether it is marked as done.
     * @param content Description of the deadline task.
     * @param by Datetime of the deadline.
     * @param isDone Indicator to indicate whether the deadline task is done.
     * @throws NyxException If the wrong datetime format is given.
     */
    public Deadline(String content, String by, boolean isDone) throws NyxException {
        super(content, isDone);
        this.by = DateTimeHandler.parseDateTime(by);
    }

    /**
     * Constructs an uncompleted deadline task with its description and deadline.
     * @param content Description of the deadline task.
     * @param by Datetime of the deadline.
     * @throws NyxException If the wrong datetime format is given.
     */
    public Deadline(String content, String by) throws NyxException {
        this(content, by, false);
    }

    /**
     * Changes the datetime associated with the DeadLine task.
     * @param newDateTime New datetime to change to.
     * @throws NyxException If the wrong datetime format is given.
     */
    public void changeDateTime(String newDateTime) throws NyxException {
        this.by = DateTimeHandler.parseDateTime(newDateTime);
    }

    /**
     * Returns a String representation of the deadline task in the format required for saving into hard disk.
     * @return String representation of the deadline task in the format required to save into hard disk.
     */
    @Override
    public String formatData() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern(DateTimeHandler.DATETIME_FORMAT));
        return String.format("D, %d, %s, %s\n", getStatusInt(), getContent(), dateFormat);
    }

    /**
     * Returns the String representation of the deadline task.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, h:ma"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }
}
