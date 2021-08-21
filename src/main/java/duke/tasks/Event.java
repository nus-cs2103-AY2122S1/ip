package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String marker = "E";
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Event(String name, LocalDateTime time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String getTime() {
        return this.time.format(formatter);
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy EEE HH:mm")) + ")";
    }
}