import exceptions.DukeException;
import exceptions.DukeIllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;
    private static final String label = "E";

    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.isEmpty()) {
            throw new DukeIllegalFormatException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalFormatException(
                    "☹ OOPS!!! Seems like you have entered a wrong date format. " +
                            "Try this instead: YYYY-MM-DD"
            );
        }
    }

    @Override
    public String toString() {
        return "[" + label + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toDataString() {
        return label + super.toDataString() + " | " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}


