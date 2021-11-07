package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.CommandParamException;

/**
 * This is a Deadline class that extends Task.
 */
public class Deadline extends TaskWithDateTime {

    /**
     * This is a Deadline Constructor.
     *
     * @param description A String representing the description of the deadline.
     * @param by A String representing the date and time of deadline.
     * @throws CommandParamException An exception thrown when the date and time is not formatted properly.
     */
    public Deadline(String description, String by) throws CommandParamException {
        super(description);
        assert !by.equals("") : "Deadline \"by\" field cannot be empty!";
        assert !description.equals("") : "Deadline \"description\" field cannot be empty!";
        String[] dateTime = by.trim().split(" ");
        // index 0 is date, 1 is time
        // date should be yyyy-mm-dd, time should be 2359 format
        if (dateTime.length != 2) { // guard clause to ensure proper dateTime format
            throw new CommandParamException("deadline");
        }
        if (dateTime[1].length() < 4) { // guard clause to ensure proper time format
            throw new CommandParamException("deadline");
        }
        try {
            String timeReformatted = dateTime[1].substring(0, 2) + ":" + dateTime[1].substring(2, 4);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            LocalTime time = LocalTime.parse(timeReformatted);
            this.date = date;
            this.time = time;
        } catch (DateTimeParseException e) {
            throw new CommandParamException("deadline");
        }
    }

    @Override
    public String fullCommand() {
        return "deadline " + this.description + " /by "
                + this.date.toString()
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("kkmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "
                + this.time.format(DateTimeFormatter.ofPattern(("h:mma")))
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            if (!(o instanceof Deadline)) {
                return false;
            } else {
                Deadline other = (Deadline) o;
                return other.date.equals(this.date) && other.time.equals(this.time);
            }
        } else {
            return false;
        }
    }
}
