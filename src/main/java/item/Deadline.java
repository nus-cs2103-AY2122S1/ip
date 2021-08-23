package item;

import exception.EmptyCommandException;

/**
 * Deadline is a type of Task which has a due time.
 */
public class Deadline extends Task {
    private String by;

    private Deadline(String description, String by) throws EmptyCommandException {
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
    public static Deadline of(String description, String by) throws EmptyCommandException {
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
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStringInDoc() {
        String s = super.toStringInDoc();
        String s1 = String.format("D | %s | %s", s, this.by);
        return s1;
    }
}