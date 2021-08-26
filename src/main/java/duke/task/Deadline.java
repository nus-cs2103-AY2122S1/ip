package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    //date of deadline in format LocalDate
    protected LocalDate byDate;
    //time of deadline in format String
    protected String byTime;

    /**
     * Constructor for Deadline Task.
     *
     * @param description String input of description of Deadline Task.
     * @param by          String for Deadline due format dd/mm/yyyy HHmm.
     * @throws DukeException          if by is incomplete.
     * @throws DateTimeParseException date is in incorrect format.
     */
    public Deadline(String description, String by) throws DukeException, DateTimeParseException {
        super(description);
        String[] parts = by.split(" ");//split along the whitespace to get the integer
        if (parts.length <= 1) {// checking for incomplete prompts, no time provided
            throw new DukeException();
        }
        String date = parts[0].replace("/", "-");
        this.byDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        this.byTime = parts[1];

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + byTime + ")";
    }
}
