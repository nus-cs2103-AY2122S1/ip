package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String type;
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.type = "Event";
        this.time = time;
    }

    public String addToFile() {
        int status = super.isDone == true ? 1 : 0;
        return "E | " +  status + " | " + this.description + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}