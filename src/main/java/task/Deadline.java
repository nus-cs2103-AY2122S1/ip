package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String name, String by) throws DukeException {
        super(name);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("incorrect date format");
        }

    }

    @Override
    public String saveTask() {
        return "deadline|" + this.getName() + "/by" + by + (this.isDone() ? "|1" : "|0");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
