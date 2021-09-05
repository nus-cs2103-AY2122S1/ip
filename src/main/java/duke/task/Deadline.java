package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toFileFormat() {
        char done = '0';
        if (super.isDone) {
            done = '1';
        }
        return "D | " + done + " | " + getDescription() + " | " + getDate();
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
