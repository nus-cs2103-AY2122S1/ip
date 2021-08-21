package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String input) {
        super(input.split(" /")[0].substring(6));
        this.at = LocalDate.parse(input.split(" /")[1].substring(3));
    }

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    private String getTime() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}