package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents a deadline, which is a subtype of a Task.
 */
public class Deadline extends Task {

    protected LocalDate date = null;
    protected LocalTime time = null;

    /**
     * A constructor of a Deadline.
     *
     * @param description Description of the Deadline.
     * @param by The date and/or time by which the task should be done.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            if (by.contains(" ")) {
                this.date = LocalDate.parse(by.split(" ", 2)[0]);
                this.time = LocalTime.parse(by.split(" ", 2)[1]);
            } else if (by.contains("-")) {
                this.date = LocalDate.parse(by);
            } else {
                this.time = LocalTime.parse(by);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Use the right format for date and/or time: yyyy-MM-dd and/or HH:mm");
        }
    }

    /**
     * Returns a string representation of a Deadline.
     *
     * @return [D], the description, the date and/or the time.
     */
    @Override
    public String toString() {
        String toPrint = "[D]" + super.toString() + " (by:";
        if (date != null) {
            toPrint += " " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
        if (time != null) {
            toPrint += " " + time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return toPrint + ")";
    }
}
