package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HHmm");

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, inputFormatter);
    }

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(outputFormatter) + ")";
    }
}
