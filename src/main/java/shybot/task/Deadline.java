package shybot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import shybot.exception.ShyBotException;
import shybot.exception.ShyBotIllegalFormatException;

/**
 * Deadline class encapsulates a deadline task.
 */
public class Deadline extends Task {
    /**
     * Deadline Label ie. "D"
     */
    private static final String LABEL = "D";
    /**
     * Deadline
     */
    protected LocalDate deadlineDate;

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description  Description.
     * @param deadlineDate Deadline.
     * @throws ShyBotException If either the description or deadline does not follow the format.
     */
    public Deadline(String description, String deadlineDate, String[] tags) throws ShyBotException {
        super(description, tags);
        if (deadlineDate.isEmpty()) {
            throw new ShyBotIllegalFormatException("☹ OOPS!!! The deadline cannot be empty.");
        }
        try {
            this.deadlineDate = LocalDate.parse(deadlineDate);
        } catch (DateTimeParseException e) {
            throw new ShyBotIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong date format. " + "Try this instead: YYYY-MM-DD"
            );
        }
    }

    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (deadlineDate: " + this.deadlineDate
            .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + ")" + " (tags: " + String.join(", ", this.tags)
            + ")";
    }

    @Override
    public String toDataString() {
        return LABEL + super.toDataString() + " | " + this.deadlineDate + " | " + String.join(" ", this.tags);
    }

}
