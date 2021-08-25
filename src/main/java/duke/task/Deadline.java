package duke.task;

import duke.exception.DukeException;
import duke.task.Task;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

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

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by;
    }

}
