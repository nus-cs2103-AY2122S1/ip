package duke.task;

import duke.IrisException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String name, String by) throws IrisException {
        super(name);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new IrisException("Invalid date provided.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

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
