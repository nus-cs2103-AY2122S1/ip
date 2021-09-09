package duke.tasks;

import duke.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  The deadline class is a subclass of Task representing a deadline
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initialises the description and end date of a deadline
     *
     * @param description description of deadline
     * @param by end date of deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public void setBy(String editedDate) {
        this.by = DateTimeParser.deadlineDateParse(editedDate);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (by: " + getDateString() + ")";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the Deadline's date into a String
     *
     * @return String representation of a date
     */
    @Override
    public String getDateString() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
