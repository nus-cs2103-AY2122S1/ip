package aisu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import aisu.exception.AisuException;

/**
 * A Deadline task.
 * Consists of a description and a deadline, written in a valid date-time format.
 *
 * @author Liaw Xin Yan
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructor to initialize the Deadline Task with a description.
     * @param description A writeup of the task details.
     * @param deadline The deadline, in java LocalDate format, of the task.
     * @throws AisuException if there is no description.
     */
    public Deadline(String description, String deadline) throws AisuException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (java.time.format.DateTimeParseException e) {
            // invalid format, throw back to Aisu.
            throw new AisuException("Invalid date/date format! Please check again");
        }
    }

    /** {@inheritDoc} */
    @Override
    public String parseData() {
        return "D;;" + (this.isDone ? "1" : "0") + ";;" + this.description + ";;" + this.deadline
                + '\n' + this.tagsList.parseData();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("[DeadL] %s %s (by: %s)%s", this.getStatusIcon(), this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.getTags());
    }
}
