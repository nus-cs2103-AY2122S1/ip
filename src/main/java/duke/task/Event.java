package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents a Task Event that happens at a date and time.
 */
public class Event extends Task {
    //date of event in format LocalDate
    protected LocalDate atDate;
    //time of event in format String
    protected String atTime;

    /**
     * Constructs a Event Task with date and time of when it is happening.
     *
     * @param description Description of Event Task.
     * @param at Date and time when Event takes place, String format dd/mm/yyyy HHmm.
     * @throws DukeException if at is incomplete, no time given.
     * @throws DateTimeParseException If date is in the wrong format.
     */
    public Event(String description, String at) throws DukeException, DateTimeParseException {
        super(description);
        String[] parts = at.split(" "); //split along the whitespace to get the integer
        if (parts.length <= 1) { // checking for incomplete prompts, no time provided
            throw new DukeException();
        }
        String date = parts[0].replace("/", "-");
        this.atDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        this.atTime = parts[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " " + atTime + ") \n";
    }
}
