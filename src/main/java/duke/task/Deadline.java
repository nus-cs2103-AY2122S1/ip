package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date.
 * E.g. submit assignment by Nov-9-2021.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Class constructor.
     *
     * @param description Description of the Deadline.
     * @param by Date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the date of the deadline formatted as [MMM-dd-yyyy]
     *
     * @return The formatted date
     */
    public String getDate() {
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = by.format(dateFormatObj);
        return formattedDate;
    }

    /**
     * Returns formatted string to write to the duke.txt file.
     *
     * @return String to write to duke.txt
     */
    @Override
    public String toWrite() {
        String done = this.isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", done, this.getDescription(), this.by);
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
