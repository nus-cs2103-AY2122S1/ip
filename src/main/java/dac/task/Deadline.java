package dac.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dac.exception.InvalidInputException;

/**
 * Represents a deadline task. A deadline task has to be completed by a certain date and time.
 */
public class Deadline extends Task {

    private static final char LABEL = 'D';
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private final LocalDateTime deadline;

    /**
     * Constructor.
     *
     * @param details Details of the deadline task.
     * @param deadline Deadline of the deadline task.
     */
    public Deadline(String details, String deadline) throws InvalidInputException {
        super(LABEL, details);
        try {
            this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("To create an Deadline task, "
                    + "Date and Time should be in the format: yyyy-mm-dd (24hr time).");
        }
    }

    /**
     * Returns deadline of task.
     *
     * @return Task deadline.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns date of task.
     *
     * @return Task deadline.
     */
    @Override
    public LocalDateTime getDate() {
        return getDeadline();
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMATTER) + ")";
    }
}
