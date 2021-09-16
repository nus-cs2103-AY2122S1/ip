package cygnus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline which is a subtype of Task.
 * A Deadline encapsulates an additional LocalDate
 * which represents the due date of the Deadline.
 *
 * @author Joshua Yong
 */
public class Deadline extends Task {

    private static final String datePrintFormat = "EEE dd MMM yyyy";

    protected LocalDate by;

    /**
     * Class constructor.
     *
     * @param description The given Deadline description.
     * @param by The Deadline LocalDate.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns this Deadline as a String.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePrintFormat);
        return "[D]" + super.toString() + " | by: " + by.format(dateFormatter);
    }

}
