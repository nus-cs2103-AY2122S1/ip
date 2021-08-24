package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("EEE dd MMM yyyy");
        return "[D]" + super.toString() + " | by: " + by.format(printFormat);
    }

}
