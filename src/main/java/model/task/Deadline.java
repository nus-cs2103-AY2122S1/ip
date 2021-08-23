package model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;
    public Deadline(String entry, String dueDate) {
        super(entry);
        this.dueDate = LocalDate.parse(dueDate);
    }

    private String dateTimeFormatter(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeFormatter(dueDate) + ")";
    }
}
