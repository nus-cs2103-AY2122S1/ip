package model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startTime;

    public Event(String entry, Boolean status, String startTime) {
        super(entry, status);
        this.startTime = LocalDate.parse(startTime);
    }

    public Event(String entry, String startTime) {
        this(entry, false, startTime);
    }

    private String dateTimeFormatter(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter(startTime) + ")";
    }
}
