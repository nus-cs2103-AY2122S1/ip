package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startTime;

    public Event(String description, String startTime) {
        super(description);
        this.startTime = LocalDate.parse(startTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + "| " + this.startTime;
    }
}
