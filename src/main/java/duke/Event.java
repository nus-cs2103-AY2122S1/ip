package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String description;
    protected boolean isDone;
    final String EVENT = "[E]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.dateAndTime = dateAndTime;
    }

    public String getDate() {
        return this.dateAndTime;
    }

    public void formatLocalDateTime() {
        if (this.dateAndTime.substring(0, 1).matches("[0-9]")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.localDateTime = LocalDateTime.parse(dateAndTime, dateTimeFormatter);
        } else {
            this.localDateTime = LocalDateTime.parse(dateAndTime, dtf);
        }
    }

    @Override
    public String toString() {
        formatLocalDateTime();
        return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: " + localDateTime.format(dtf) + ")";
    }
}
