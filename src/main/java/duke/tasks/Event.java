package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String marker = "[E]";
    private LocalDateTime time;

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return this.marker + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy EEE HH:mm")) + ")";
    }
}