package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {

        return "E " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
