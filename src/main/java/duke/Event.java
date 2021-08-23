package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        LocalDate d = LocalDate.parse(at);
        this.at = d;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        LocalDate d = LocalDate.parse(at);
        this.at = d;
    }

    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}