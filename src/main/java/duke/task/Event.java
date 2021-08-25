package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected LocalTime time;
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return at;
    }

    public String formatSave() {
        if (time != null) {
            return "E | " + ((super.isDone) ? "1 |" : "0 | ") + super.getDescription() + " | " +
                    date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", " + time;
        } else if (date != null) {
            return "E | " + ((super.isDone) ? "1 |" : "0 | ") + super.getDescription() + " | " +
                    date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "E | "  + ((super.isDone) ? "1 |" : "0 | ") + super.getDescription() + " | " + getTime();
    }

    @Override
    public String toString() {
        if (time != null) {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", " + time + ")";
        } else if (time != null) {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + at + ")";
        }
    }
}
