package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String serialize() {
        return String.join(" | ", "E", this.done ? "1" : "0", this.description, this.at);
    }

    @Override
    public String toString() {
        String date = this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
