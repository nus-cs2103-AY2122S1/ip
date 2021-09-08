package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime dateTime;


    public Event(String description, String date) {
        // Initialised based on Task constructor
        super(description);

        // Compute format for dateTime to be scanned as
        DateTimeFormatter scanned = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(date, scanned);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return "[E]" + super.toString() + " (at: " + this.dateTime.format(dateFormat) + ")";
    }
}
