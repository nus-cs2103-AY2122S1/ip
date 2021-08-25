package jared.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Converts to string for printing list.
     * @return String of event details.
     */
    @Override
    public String toString() {
        String res = "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (time != null) {
            res += " " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return res += ")";
    }

    /**
     * Converts to string for saving in data.
     * @return String of event details in save format.
     */
    @Override
    public String saveFormat() {
        String res = "E _ " + super.saveFormat() + " _ " + this.date;
        if (time != null) {
            res += " _ " + this.time;
        }
        return res;
    }
}
