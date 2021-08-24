package BobCat.model.task;

import BobCat.exception.LogicException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate dueDate;
    public Deadline(String entry, Boolean status, String dueDate) {
        super(entry, status);
        try {
            this.dueDate = LocalDate.parse(dueDate);
        } catch (DateTimeParseException e) {
            throw new LogicException("Cannot understand given date. Is it in \"yyyy-mm-dd\" format?");
        }
    }

    public Deadline(String entry, String dueDate) {
        this(entry, false, dueDate);
    }

    private String formatDateTime(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(dueDate) + ")";
    }
}
