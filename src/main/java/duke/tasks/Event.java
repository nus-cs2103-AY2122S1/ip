package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;
    private LocalDateTime end;

    public Event(String description, boolean isDone, LocalDateTime at, LocalDateTime end) {
        super(description, isDone);
        this.at = at;
        this.end = end;
    }

    public Event(String description, LocalDateTime at, LocalDateTime end) {
        this(description, false, at, end);
    }

    @Override
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + at + " | " + end;
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString();
        if (end == null) {
            s += " (at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
        } else {
            s += " (from: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma — "))
                    + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
        }
        return s;
    }
}