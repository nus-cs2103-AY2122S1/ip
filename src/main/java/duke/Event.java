package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task{

    protected LocalDate eventDate;
    public Event(String description, LocalDate eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    private String formatDate() {
        return eventDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + formatDate() + ")";
    }
}