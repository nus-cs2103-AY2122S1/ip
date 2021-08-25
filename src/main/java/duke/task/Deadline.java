package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class encapsulates a deadline task.
 */
public class Deadline extends Task {
    /**
     * Deadline
     */
    protected LocalDate by;
    /**
     * Deadline Label ie. "D"
     */
    private static final String LABEL = "D";

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description Description.
     * @param by          Deadline.
     * @throws DukeException If either the description or deadline does not follow the format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.isEmpty()) {
            throw new DukeIllegalFormatException("☹ OOPS!!! The deadline cannot be empty.");
        }
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalFormatException(
                    "☹ OOPS!!! Seems like you have entered a wrong date format. " +
                            "Try this instead: YYYY-MM-DD"
            );
        }
    }

    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return LABEL + super.toDataString() + " | " + by;
    }

}
