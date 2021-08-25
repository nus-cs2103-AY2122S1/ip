package duke.task;

import duke.exception.DukeException;
import duke.task.Task;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the deadline.
     * @param by Date to complete the task.
     * @throws DukeException If the format of date is incorrect.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by.replaceAll("\\s", ""));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in this format: YYYY-MM-DD");
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Convert the task into a string that can be store in the file.
     *
     * @return the text format of the task to be store in the file.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by;
    }

}
