package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    private static final String label = "D";


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
        return "[" + label + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return label + super.toDataString() + " | " + by;
    }

}
