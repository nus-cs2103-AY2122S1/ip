package duke.tasks;

import duke.exceptions.EmptyDeadlineBodyException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidDeadlineBodyException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a completable <code>Task</code> with a description and a date to be completed by.
 *
 * @author kevin9foong
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Instantiates a <code>Deadline</code> from its data String representation.
     *
     * @param deadlineDataText String containing data in the form 'description /by yyyy-mm-dd'.
     * @throws EmptyDeadlineBodyException     thrown when deadline description or by date are not provided.
     * @throws InvalidDeadlineBodyException   thrown when the data String representation is invalid.
     * @throws InvalidDateTimeFormatException thrown when the <code>DateTime</code> format for the by date provided
     *                                        is invalid.
     */
    public Deadline(String deadlineDataText) throws EmptyDeadlineBodyException, InvalidDeadlineBodyException,
            InvalidDateTimeFormatException {
        if (deadlineDataText == null || deadlineDataText.isEmpty()) {
            throw new EmptyDeadlineBodyException();
        }
        String[] deadlineData = deadlineDataText.split("/by ", 2);
        if (deadlineData.length != 2 || deadlineData[0].isEmpty() || deadlineData[1].isEmpty()) {
            throw new InvalidDeadlineBodyException();
        }
        super.setDescription(deadlineData[0].trim());
        try {
            this.by = LocalDate.parse(deadlineData[1].trim());
        } catch (DateTimeParseException dte) {
            throw new InvalidDateTimeFormatException();
        }
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super.setDescription(description);
        super.setIsDone(isDone);
        this.by = by;
    }

    @Override
    public String getTaskRepresentation() {
        return TaskType.DEADLINE + "," + super.getTaskRepresentation() + this.by + ",";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.getMonth() + " " + by.getDayOfMonth() + " " + by.getYear() + ")";
    }
}
