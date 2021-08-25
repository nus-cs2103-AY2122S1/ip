package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDate date;
    private final LocalTime start;
    private final LocalTime end;

    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", "
                + start.format(DateTimeFormatter.ofPattern("h:mm a"))
                + " - "
                + end.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
