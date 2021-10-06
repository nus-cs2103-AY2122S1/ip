package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * This class represents a {@code Deadline} task.
 *
 * @author Eizabeth Chow
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy H:mm");
    private static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    // End date of the Deadline object
    private LocalDateTime endDate;

    /**
     * Constructs a {@code Deadline} object with the given title and end date.
     *
     * @param title   Title of {@code Deadline}.
     * @param endDate End date of {@code Deadline}.
     */
    public Deadline(String title, String endDate) {
        super(title);
        this.endDate = parseDateTime(endDate.trim());
    }

    /**
     * Constructs a {@code Deadline} object with the given title, end date and
     * status.
     *
     * @param title   Title of {@code Deadline}.
     * @param endDate End date of {@code Deadline}.
     * @param isDone  Status of {@code Deadline}.
     */
    public Deadline(String title, String endDate, boolean isDone) {
        super(title, isDone);
        this.endDate = parseDateTime(endDate.trim());
    }

    public Deadline setDate(String endDate) {
        this.endDate = parseDateTime(endDate);
        return this;
    }

    private LocalDateTime parseDateTime(String date) throws DukeException {
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(date, FORMATTER);
            return parsedDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid datetime format for Deadline task.\n"
                    + "Only take in datetime with the format d-M-yyyy H:mm\n"
                    + "Note that time should be in 24 hour format.");
        }
    }

    /**
     * {@inheritDoc} Adds "D |" infront to indicate that it is a {@code Deadline}
     * task.
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), endDate.format(FORMATTER));
    }

    /**
     * Returns a String representation of a Deadline task. Starts "[D]" to indicate
     * that it is a Deadline task.
     *
     * @return String representation of an Deadline.
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endDate.format(PRINT_FORMATTER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.endDate.equals(otherDeadline.endDate) && super.equals(other);
        }
        return false;
    }
}
