package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final String identifier = "E";
    private LocalDateTime eventDateTime;
    private boolean isDateAndTime;
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String description, String eventDate) throws DateTimeParseException {
        super(description);
        this.eventDate = LocalDate.parse(eventDate);
    }

    public Event(String description, String eventDate, String eventTime) throws DateTimeParseException {
        super(description);
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
        isDateAndTime = true;
        this.eventDateTime = LocalDateTime.of(this.eventDate, this.eventTime);
    }

    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += isDateAndTime 
                ? " (at: " + this.eventDateTime.format(super.dateTimePattern) + ")"
                : " (at: " + this.eventDate.format(super.datePattern) + ")";
        return result;
    }

    @Override
    public String databaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|";
        result += isDateAndTime ? this.eventDate + "|" + this.eventTime : this.eventDate;
        return result;
    }
}
