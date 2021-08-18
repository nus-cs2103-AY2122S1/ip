package item;

import exception.BotException;
import exception.EmptyCommandException;

/**
 * Deadline is a type of Task which has a due time.
 */
public class Deadline extends Task {
    protected String by;

    private Deadline(String description, String by) throws EmptyCommandException {
        super(description);
        this.by = by;
    }

    /**
     * Create a Deadline.
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
     * Return the string representation of Deadline.
     * @return The string format of Deadline, including status, description and due time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}