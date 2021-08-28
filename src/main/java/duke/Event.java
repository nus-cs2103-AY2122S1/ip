package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public LocalDate getEventDate() {
        return at;
    }

    @Override
    public String toStringData() {
        return "E" + super.toStringData() + "|" + at;
    }

    @Override
    public boolean matches(String query) {
        return super.matches(query)
                || at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toLowerCase().contains(query.toLowerCase())
                || query.equalsIgnoreCase("event");
    }
}