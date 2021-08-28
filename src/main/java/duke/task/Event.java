package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class that represents an event task */
public class Event extends Task {
    private LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + at);
        }
    }

    public Event(String description, String at, boolean done) throws DukeException {
        super(description, done);

        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + at);
        }
    }

    @Override
    public String serialize() {
        return String.join(" | ", "E", this.done ? "1" : "0", this.description, this.at.toString());
    }

    @Override
    public String toString() {
        String date = this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
