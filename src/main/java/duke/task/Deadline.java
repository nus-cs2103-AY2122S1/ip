package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byWhen;

    public Deadline(String description, String byWhen) {
        super(description);
        this.byWhen = LocalDate.parse(byWhen);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + "| " + this.byWhen;
    }
}
