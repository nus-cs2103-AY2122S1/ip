package BobCat.model.task;

import BobCat.exception.LogicException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate startTime;

    public Event(String entry, Boolean status, String startTime) {
        super(entry, status);
        try {
            this.startTime = LocalDate.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new LogicException("Cannot understand given date. Is it in \"yyyy-mm-dd\" format?");
        }
    }

    public Event(String entry, String startTime) {
        this(entry, false, startTime);
    }

    private String formatDateTime(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDateTime(startTime) + ")";
    }
}
