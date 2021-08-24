package catobot.item;

import catobot.exception.EmptyCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task which has a due time.
 */
public class Deadline extends Task {
    /** Due datetime of the deadline. */
    private final LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description The details of deadline.
     * @param by The due datetime.
     * @throws EmptyCommandException If the description is empty.
     */
    private Deadline(String description, LocalDate by) throws EmptyCommandException {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline.
     *
     * @param description The description of Deadline details.
     * @param by The due date of the Deadline.
     * @return The created Deadline
     * @throws EmptyCommandException if the description is empty.
     */
    public static Deadline of(String description, LocalDate by) throws EmptyCommandException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("deadline");
        } else {
            return new Deadline(description, by);
        }
    }

    /**
     * Returns the string representation of Deadline.
     *
     * @return The string format of Deadline, including status, description and due time.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ")";
    }

    /**
     * Represents the format of deadline in storage.
     *
     * @return The string representation of deadline in storage.
     */
    @Override
    public String toStringInDoc() {
        String s = super.toStringInDoc();
        String s1 = String.format("D | %s | %s", s, this.by);
        return s1;
    }
}