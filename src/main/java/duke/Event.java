package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    private LocalDate eventDate;

    public Event(String description, String at) {
        super(description);
        this.eventDate = LocalDate.parse(at);
        this.at = at;
    }

    @Override
    public char getTaskType() { return 'E'; }

    @Override
    public String getTime() {
        return this.at;
    }

    @Override
    public String toString() {
        String formattedDate = this.eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "  [E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
