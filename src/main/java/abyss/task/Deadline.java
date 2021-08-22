package abyss.task;

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
        return "  [D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String toFileEntry() {
        return "D | " + super.getIsDone() + " | " + super.description + " | " + this.by;
    }
}
