package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public String addToFile() {
        return "E | 0 | " + this.description + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}