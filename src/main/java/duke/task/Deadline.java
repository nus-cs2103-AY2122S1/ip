package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents a Task with a Deadline(Date and Time).
 */
public class Deadline extends Task {


    /**
     * Constructs a Task with a Deadline.
     *
     * @param description String input of description of Deadline Task.
     * @param by String for Deadline due format dd/mm/yyyy HHmm.
     * @throws DukeException if by is incomplete.
     * @throws DateTimeParseException date is in incorrect format.
     */
    public Deadline(String description, String by) throws DukeException, DateTimeParseException {
        super(description);
        String[] parts = by.split(" "); //split along the whitespace to get the integer
        if (parts.length <= 1) { // checking for incomplete prompts, no time provided
            throw new DukeException();
        }
        String date = parts[0].replace("/", "-");
        this.Date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        assert parts[1] != null;
        this.Time = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " " + Time + ") \n";
    }
}
