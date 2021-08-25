package jared.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Converts to string for printing list.
     * @return String of deadline details.
     */
    @Override
    public String toString() {
        String res = "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (time != null) {
            res += " " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return res += ")";
    }

    /**
     * Converts to string for saving in data.
     * @return String of deadline details in save format.
     */
    @Override
    public String saveFormat() {
        String res = "D _ " + super.saveFormat() + " _ " + this.date;
        if (time != null) {
            res += " _ " + this.time;
        }
        return res;
    }
}