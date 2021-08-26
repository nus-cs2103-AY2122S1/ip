package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + by);
        }
    }

    public Deadline(String description, String by, boolean done) throws DukeException {
        super(description, done);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + by);
        }
    }

    @Override
    public String serialize() {
        return String.join(" | ", "D", this.done ? "1" : "0", this.description, this.by.toString());
    }

    @Override
    public String toString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
