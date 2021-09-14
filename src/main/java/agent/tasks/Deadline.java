package agent.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import agent.exceptions.EmptyDeadlineBodyException;
import agent.exceptions.InvalidDateTimeFormatException;
import agent.exceptions.InvalidDeadlineBodyException;

/**
 * Represents a completable <code>Task</code> with a description and a date to be completed by.
 *
 * @author kevin9foong
 */
public class Deadline extends Task {
    private final LocalDate dueDate;

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
        boolean isDeadlineBodyInvalid = deadlineData.length != 2
                || deadlineData[0].isEmpty() || deadlineData[1].isEmpty();
        if (isDeadlineBodyInvalid) {
            throw new InvalidDeadlineBodyException();
        }
        super.setDescription(deadlineData[0].trim());
        try {
            this.dueDate = LocalDate.parse(deadlineData[1].trim());
        } catch (DateTimeParseException dte) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Constructs an instance of <code>Deadline</code> with the provided description,
     * completion status and deadline.
     *
     * @param description description of what the <code>Deadline</code> entails.
     * @param isDone      completion status of <code>Deadline</code>.
     * @param dueDate     deadline which <code>Deadline</code> must be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate dueDate) {
        super.setDescription(description);
        super.setIsDone(isDone);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    @Override
    public String getTaskRepresentation() {
        return TaskType.DEADLINE + " |;; " + super.getTaskRepresentation() + this.dueDate + " |;; ";
    }

    /**
     * Returns the <code>String</code> representation representing the task type,
     * completion status, description and deadline of this <code>Deadline</code>.
     *
     * @return <code>String</code> representation of this <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate.getMonth() + " " + dueDate.getDayOfMonth() + " " + dueDate.getYear() + ")";
    }
}
