package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            atDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            atDate = null;
        }
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    public String parseAtDate() {
        return getAtDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + ((atDate == null) ? at : parseAtDate())
                + ")";
    }
}