package model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;
    public Deadline(String entry, Boolean status, String dueDate) {
        super(entry, status);
        this.dueDate = LocalDate.parse(dueDate); // Need to find a better way to parse
    }

    public Deadline(String entry, String dueDate) {
        this(entry, false, dueDate);
    }

    private String dateTimeFormatter(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeFormatter(dueDate) + ")";
    }
}
