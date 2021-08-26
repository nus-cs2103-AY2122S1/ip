package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime dateTimeAt;
    protected String at;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.dateTimeAt = LocalDateTime.parse(at, formatter);
    }

    public Event(String num, String description, String at) {
        this(description, at);
        this.isDone = !num.equals("0");
        this.dateTimeAt = LocalDateTime.parse(at, formatter);
    }

    public String getFormattedAt() {
        return this.dateTimeAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    @Override
    public String getFileString() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.at);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getFormattedAt() + ")";
    }


}