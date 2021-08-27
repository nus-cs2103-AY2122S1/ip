package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy H:mm");
    private final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    // End date of the Deadline object
    private final LocalDateTime endDate;

    /**
     * Constructor of a Deadline object.
     *
     * @param title   title of the Deadline task
     * @param endDate endDate of the Deadline task
     */
    public Deadline(String title, String endDate) {
        super(title);
        this.endDate = parseDateTime(endDate.trim());
    }

    public Deadline(String title, String endDate, boolean isDone) {
        super(title, isDone);
        this.endDate = parseDateTime(endDate.trim());
    }

    private LocalDateTime parseDateTime(String date) {
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(date, FORMATTER);
            return parsedDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid datetime format for Deadline task.\n"
                    + "Only take in datetime with the format d-M-yyyy H:mm\n"
                    + "Note that time should be in 24 hour format.");
        }
    }

    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), endDate.format(FORMATTER));
    }

    /**
     * Return a String representation of a Deadline task. Starts "[D]" to indicate
     * that it is a Deadline task.
     *
     * @return String representation of an Deadline.
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endDate.format(PRINT_FORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.endDate.equals(otherDeadline.endDate) && super.equals(other);
        }
        return false;
    }
}
