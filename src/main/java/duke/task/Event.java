package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate duration;
    protected LocalTime time;

    public Event(String description, String duration, String time) {
        super(description);
        this.duration = LocalDate.parse(duration);
        this.time = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDate() + " " + this.formatTime() + ")";
    }

    public String formatDate() {
        return this.duration.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String formatTime() {
        return this.time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

}