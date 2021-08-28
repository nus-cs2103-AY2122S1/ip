package aisu.task;

import aisu.exception.AisuException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task.
 * Consists of a description and a deadline, written in a valid date-time format.
 *
 * @author Liaw Xin Yan
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String description, String deadline) throws AisuException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (java.time.format.DateTimeParseException e) {
            // invalid format, throw back to Aisu.
            throw new AisuException("Invalid date/date format! Please check again");
        }
    }

    /**
     * Parses data in readable format to be stored in storage.
     *
     * @return Parsed data.
     */
    @Override
    public String parseData() {
        return "D;;" + (this.isDone ? "1" : "0") + ";;" + this.description + ";;" + this.deadline;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return The Task in readable format.
     */
    @Override
    public String toString() {
        return String.format("[DeadL] %s %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
