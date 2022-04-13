package catobot.item;

import java.time.LocalDateTime;

import catobot.exception.EmptyCommandException;

/**
 * Represents a type of Task which has a due time.
 */
public class Deadline extends Task {
    /** The datetime of the deadline. */
    private final LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description The details of deadline.
     * @param by The due datetime.
     * @throws EmptyCommandException If the description is empty.
     */
    private Deadline(String description, LocalDateTime by) throws EmptyCommandException {
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
    public static Deadline of(String description, LocalDateTime by) throws EmptyCommandException {
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
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DATE_FORMAT_DISPLAY));
    }

    /**
     * Represents the format of deadline in storage.
     *
     * @return The string representation of deadline in storage.
     */
    @Override
    public String toStringInDoc() {
        String s = super.toStringInDoc();
        return String.format("D | %s | %s", s, this.by.format(DATE_FORMAT_DOC));
    }


    /**
     * Compares two deadlines chronologically (the latest first).
     *
     * @param o Another task being compared with.
     * @return -1 if the datetime is later than the other
     *         1 if the datetime is earlier than the other
     *         0 if the datetimes are the same.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            Deadline ddl = (Deadline) o;
            return ddl.by.compareTo(by);
        }
        return super.compareTo(o);
    }
}
