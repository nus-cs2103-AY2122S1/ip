package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is a subtype of Task.
 * A deadline encapsulates an additional LocalDate
 * which represents the date of the deadline.
 *
 * @author Joshua Yong
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Class constructor.
     *
     * @param description The given deadline description.
     * @param by The deadline date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Outputs this deadline as a String.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("EEE dd MMM yyyy");
        return "[D]" + super.toString() + " | by: " + by.format(printFormat);
    }

}
