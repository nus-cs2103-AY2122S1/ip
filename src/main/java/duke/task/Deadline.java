package duke.task;

import duke.IrisException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline object
     * @param name name of the deadline
     * @param by   due date for this deadline e.g. "2021-08-23"
     * @throws IrisException for invalid date
     */
    public Deadline(String name, String by) throws IrisException {
        super(name);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new IrisException("Invalid date provided.");
        }
    }

    /**
     * Converts Deadline object to String
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    /**
     * Converts Deadline object to Command
     * @param index index of this Task in the TaskList
     * @return String representing the command to re-create this Deadline object
     */
    @Override
    public String toCommand(int index) {
        return String.format(
                "deadline %s /by %s\n%s",
                this.name,
                this.by,
                super.toCommand(index)
        );
    }
}
