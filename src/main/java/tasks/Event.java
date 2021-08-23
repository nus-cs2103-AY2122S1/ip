package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String time) {
        super(description);
        this.at = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedTime() +")";
    }
}
